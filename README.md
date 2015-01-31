FlashGetter
====================================================
写这个软件的目的主要在于对Java抽象编程以及java8新特性的应用（lambda表达式，StreamAPI），
  更重要的是应用自己的一些在软件结构上的创新设计（从结果上来看，新的设计似乎能够大大减少冗余代码，
  大幅度的松散耦合）。至于软件本身的功能，这不是讨论的重点，但是它可以提供基本的文件下载功能以
  及相对于Java Swing对用户更加友好的界面。
### 界面的架构设计
####特点：约定优先于类别，根据继承层次选择信息分发范围这是本程序结构上的一大创新。
  传统的界面设计模式为使用普通的监听者模式，将所有需要响应的界面注册到不同范围的消息分发器上，
某个组件要发送消息给另一个或者一批组件时，必须选择相应的分发器进行消息传递这样的设计缺点很明显，
为了对某一范围的组件进行消息分发，要不使用多个针对特定范围的分发器分发消息，要不就只能通过各种逻
辑判断来过滤掉非目标组件的消息。这就导致了分发器太多或者更多的逻辑代码冗余。更可怕的是，如果软件
日后需要拓展，面对复杂的逻辑，开发者可能无从下手，显然，这会大大提升软件的维护成本。
  新的设计分成几个部分，我们以一个简单的例子开始：例如 类 class1 class2  想往classTarget1发送消息，
而class4，class5想往classTarget2发送消息那么需要进行以下步骤：

        * 第一步是在classtarget1、classtarget2两个类继承ViewEventHandler<C>接口
```java
      public interface ViewEventHandler <C> {
      //消息发送过来时的响应方法
        public void invoke(ViewEvent event);
      //接收范围
        public Class<C> getGroupClass();
      }
```
        * 第二部：在classtarget1、classtarget2两个类构造器中向ViewEventDispatcher注册自己
```Java
      public class ViewEventDispatcher {
          //所有注册的接收者都汇集在这（classtarget1、classtarget2）
          private List<ViewEventHandler> listeners;
          //ViewEventDispatcher为单例
          public static class InnerClass{
              public static final ViewEventDispatcher instance = new ViewEventDispatcher();
          }
          private ViewEventDispatcher() {
              listeners = new ArrayList<ViewEventHandler>();
          }
          //注册方法 classtarget1、classtarget2调用它
          public void register(ViewEventHandler handler){
              listeners.add(handler);
          }
          //此方法为发送者调用，注意到，分发器并不是将消息向所有接收者分发
          //而是根据接收者实现的getGroupClass()方法确定分发范围
          public void fireEvent(ViewEvent event){
              if(event == null) return;
                  listeners.stream()
                  .filter(target -> target.getGroupClass().isAssignableFrom(event.getTarget()))
                  .forEach(listener -> listener.invoke(event));
          }
      
      }
```
        * 第三部：class1、class2、class3、class4 通过ViewEventDispatcher.fireEvent（）方法发送
        ViewEvent，ViewEvent目前有两个字段：
```Java
      private Class<?> target;
      private Object data;
      
      public ViewEvent setTarget(Class<?> target) {
        this.target = target;
        return this;
      }
    
      public ViewEvent setData(Object data) {
        this.data = data;
        return this;
      }
```
target为接收范围
data为需要传送的消息
  假如classtarget1、classtarget2实现的getGroupClass返回值分别为T1.class、T2.class，
则class1、class2发送target为T1.class的event即可，class3、4同理这种设计的好处就是，
可以直接以继承结构发送消息，很典型的例子就是给一个统一接口（或父类）的某个子类发送消息。
只需在此模块管理类实现ViewEventHandler，getGroup（以T1为例）方法返回此组模块的共同父类就行了，
通过Key为Class<? extends T1> value为T1子类的实例的HashMap即可无需传递类型参数或逻辑判断轻易获
取子类。这种设计对于类层次结构分明的界面架构再适合不过，感兴趣的可以阅读源码，源码中的界面架构
大量应用了此种设计，减少了大量冗余代码和交叉引用，实际效果非常理想。


