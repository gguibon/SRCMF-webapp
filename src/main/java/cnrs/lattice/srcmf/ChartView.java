package cnrs.lattice.srcmf;
 
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import cnrs.lattice.engines.eval.Eval;
import cnrs.lattice.tools.utils.Tools;
 
@ManagedBean
//@SessionScoped
public class ChartView implements Serializable {
 
    /**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private SelectView selectView = findBean("selectView");
//	@ManagedProperty("#{selectView.tmpResPath}")
//	private String tmpResPath;//"res.conll";
	private BarChartModel animatedModel;
    private String chemin = Params.userhome + Params.dir;
    
    private String logName = "res.log";
    private float acc = 0;
    private float uas = 0;
    private float las = 0;
 
    @PostConstruct
    public void init() {
    	for(int i = 0; i < 30; i++)System.out.println(selectView.getTmpResPath());
        createAnimatedModels();
    }
 
    public BarChartModel getAnimatedModel() {
        return animatedModel;
    }
 
    private void createAnimatedModels() {
    	String title = "";
    	try {
    		List<String> log = Tools.path2liste(chemin+logName);
    		title = String.format("%s predicted with %s model", log.get(1), log.get(0)); 
			doEval(selectView.getTmpResPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        animatedModel = initBarModel();
//        animatedModel.setTitle(title);
        animatedModel.setAnimate(true);
        animatedModel.setLegendPosition("ne");
        Axis yAxis = animatedModel.getAxis(AxisType.Y);
        Axis xAxis = animatedModel.getAxis(AxisType.X);
        //xAxis.setLabel("Accuracy, Unlabelled and Labelled Attachment Scores");
        
        yAxis.setLabel("Percents");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }
     
    private BarChartModel initBarModel() {    	
    	
        BarChartModel model = new BarChartModel();
 
        ChartSeries accChart = new ChartSeries();
        accChart.setLabel("ACC");
        accChart.set("Accuracy, Unlabelled and Labelled Attachment Scores", acc);
 
        ChartSeries uasChart = new ChartSeries();
        uasChart.setLabel("UAS");
        uasChart.set("UAS", uas);
        
        ChartSeries lasChart = new ChartSeries();
        lasChart.setLabel("LAS");
        lasChart.set("LAS", las);
        
        model.addSeries(accChart);
        model.addSeries(uasChart);
        model.addSeries(lasChart);
        
        return model;
    }   
    
    private void doEval(String fileAbsPath) throws Exception{
    	if(StringUtils.isNotBlank(fileAbsPath)){
	    	las = Float.parseFloat(Eval.getLAS(8, 9, 10, 11, fileAbsPath));//chemin+fileName));
	    	las = las*100;
			uas = Float.parseFloat(Eval.getAccuracy(8, 9, fileAbsPath));
			uas = uas*100;
			acc = Float.parseFloat(Eval.getAccuracy(4, 5, fileAbsPath));
			acc = acc*100;
    	}
		System.out.println(acc + " " + uas + " " + las);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }
}