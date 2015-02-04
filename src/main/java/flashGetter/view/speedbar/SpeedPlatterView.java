package flashGetter.view.speedbar;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import flashGetter.Resources;
import flashGetter.util.ImageUtil;
import flashGetter.view.OptionPanel;
import flashGetter.view.ViewEvent;
import flashGetter.view.ViewEventDispatcher;
import flashGetter.view.ViewEventHandler;
import flashGetter.view.sidebar.SideBarView;
import flashGetter.view.sidebar.SideBarChangedView;

/**
 * @author decaywood
 * 
 * 2015年1月30日
 * 
 */
public class SpeedPlatterView extends JPanel implements ViewEventHandler<SpeedPlatterView.AbstractOption>{
    
    private OptionPanel fold;
    private OptionPanel extend;
    private JPanel buttonPanel;
    
    static interface AbstractOption{}
    private static interface Fold extends AbstractOption{}
    private static interface Extend extends AbstractOption{}
    
    public SpeedPlatterView() {
        
        setLayout(new BorderLayout());
        ViewEventDispatcher.InnerClass.instance.register(this);
        
        fold = new OptionPanel(ImageUtil.readIcon(Resources.pageChange, 30),
                ImageUtil.readIcon(Resources.pageChangeChoosed, 30),
                "Fold", 
                new ViewEvent().setTarget(SideBarChangedView.class),
                new ViewEvent().setTarget(Fold.class));
        
        extend = new OptionPanel(ImageUtil.readIcon(Resources.pageRecovered, 30),
                ImageUtil.readIcon(Resources.pageRecoveredChoosed, 30),
                "Fold", new ViewEvent().setTarget(SideBarView.class),
                new ViewEvent().setTarget(Extend.class));
        
        buttonPanel = new JPanel();
        buttonPanel.add(fold);
        
        add(new SpeedOptionPanelView(),BorderLayout.EAST);
        add(buttonPanel,BorderLayout.WEST);
        
    }

    @Override
    public void invoke(ViewEvent event) {
        buttonPanel.removeAll();
        if(event.getTarget() == Fold.class) buttonPanel.add(extend);
        if(event.getTarget() == Extend.class) buttonPanel.add(fold);
        buttonPanel.updateUI();
    }

    @Override
    public Class<AbstractOption> getGroupClass() {
        return AbstractOption.class;
    }

    

}
