package cnrs.lattice.srcmf;
 
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import cnrs.lattice.engines.eval.Eval;
import cnrs.lattice.tools.utils.Tools;
 
@ManagedBean
public class ChartView implements Serializable {
 
    private BarChartModel animatedModel;
    private String chemin = Params.dir;
    private String fileName = "res.conll";
    private float acc ;
    private float uas ;
    private float las ;
 
    @PostConstruct
    public void init() {
        createAnimatedModels();
    }
 
    public BarChartModel getAnimatedModel() {
        return animatedModel;
    }
 
    private void createAnimatedModels() {
        
    	try {
			doEval();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        animatedModel = initBarModel();
        animatedModel.setTitle("Accuracies");
        animatedModel.setAnimate(true);
        animatedModel.setLegendPosition("ne");
        Axis yAxis = animatedModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(100);
    }
     
    private BarChartModel initBarModel() {    	
    	
        BarChartModel model = new BarChartModel();
 
        ChartSeries accChart = new ChartSeries();
        accChart.setLabel("ACC");
        accChart.set("ACC", acc);
 
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
    
    private void doEval() throws Exception{
    	las = Float.parseFloat(Eval.getLAS(8, 9, 10, 11, chemin+fileName));
    	las = las*100;
		uas = Float.parseFloat(Eval.getAccuracy(8, 9, chemin+fileName));
		uas = uas*100;
		acc = Float.parseFloat(Eval.getAccuracy(4, 5, chemin+fileName));
		acc = acc*100;
		System.out.println(acc + " " + uas + " " + las);
    }
}