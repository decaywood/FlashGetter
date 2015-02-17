FlashGetter
====================================================
   写这个软件的目的主要在于对Java抽象编程以及java8新特性的应用（主要为集合以函数式风格进行操作），
更重要的是应用自己的一些在软件结构上的创新设计（从结果上来看，新的设计似乎能够大大减少冗余代码，
 大幅度的松散耦合）。至于软件本身的功能，这不是讨论的重点，但是它可以提供基本的文件下载功能以
  及相对于Java Swing对用户更加友好的界面。
  
  the main purpose of this project is to put the abstract programming
and the new feature of java8 into practice(especially using function 
programming in collection), furthermore, using the creative design
 onto software structure is the key. as for the software itself, it doens't
 make any sense in talking about its function, but it can offer the basic
 download function and more user friendly surface
 

  
### 界面的架构设计 the framework of surface

   基本结构基于功能的继承关系，将部分可通用的UI组件进行抽象，实现复用的最大化
   
 the structure of this software is based on inheritance relationships
 of function, abstract the component,and reuse them

####特点：约定优先于配置，根据继承层次控制信息分发范围这是本程序结构上的一大创新（这不代表限定在类层次）。
####advantage: regulation is superior to configure, dispatching information
####according to class inherit structure is the creative point on structure 

   传统的界面设计模式为使用普通的监听者模式，将所有需要响应的界面注册到不同范围的消息分发器上，
某个组件要发送消息给另一个或者一批组件时，必须选择相应的分发器进行消息传递,这样的设计缺点很明显，
为了对某一范围的组件进行消息分发，要不使用多个针对特定范围的分发器分发消息，要不就只能广播发送然后
通过各种逻辑判断来过滤掉非目标组件的消息。这就导致了分发器太多或者更多的逻辑代码冗余。更可怕的是，
如果软件日后需要拓展，面对复杂的逻辑，开发者可能无从下手，显然，这会大大提升软件的维护成本。
        我们以一个简单的例子开始：例如 类 class1 class2  想往classTarget1发送消息，
而class4，class5想往classTarget2发送消息那么需要进行以下步骤：


        * 第一步是在classtarget1、classtarget2两个类继承ViewEventHandler<C>接口
        C为泛型参数，为接收范围类。
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
则class1、class2发送target为T1.class的event即可，class3、4同理。这样class1、2、3、4发送
的消息就可以分别被classtarget1、2捕获，配合ViewEvent中的字段（未完善）可以灵活的处理分发的消息。
        这种设计的好处就是，可以直接以继承结构为过滤条件发送消息，很典型的例子就是给一个统一接口（或父类）的某个子类发送消息。
只需在此模块管理类（接收类）实现ViewEventHandler，getGroup（以T1为例）方法返回此组模块的共同父类（T1）
就行了。通过接收类持有的Key为Class<? extends T1> value为T1子类的实例的HashMap即可无需传递类型参数
或逻辑判断轻易获取子类从而采取相应动作（map.get(event.gettarget()).dealwith(event.getData())）。
        这种设计对于类层次结构分明的界面架构再适合不过，通过这种设计，能让你在消息分发上完成功能的前提下拥有结构上的美感，而
维护时却轻而易举。对代码感兴趣的可以阅读源码，源码中的界面架构大量应用了此种设计，减少了大量冗余代码和交叉引用，
实际效果非常理想。

###为什么需要 Stream
   Stream 作为 Java 8 的一大亮点，Java 8 中的 Stream 是对集合（Collection）对象功能的增强，
它专注于对集合对象进行各种非常便利、高效的聚合操作（aggregate operation），或者大批量数据操作
(bulk data operation)。Stream API 借助于同样新出现的 Lambda 表达式，极大的提高编程效率和程序可读性。
 同时它提供串行和并行两种模式进行汇聚操作，并发模式能够充分利用多核处理器的优势，使用 fork/join 并行方式来拆分任务
 和加速处理过程。通常编写并行代码很难而且容易出错, 但使用 Stream API 无需编写一行多线程的代码，就可以很方便地写出
 高性能的并发程序。所以说，Java 8 中首次出现的 java.util.stream 是一个函数式语言+多核时代综合影响的产物。
