package com.durasoft.client;

import com.durasoft.client.model.Employee;
import com.durasoft.client.model.EmployeeProperties;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.BarSeries;
import com.sencha.gxt.chart.client.chart.series.PieSeries;
import com.sencha.gxt.chart.client.chart.series.Series.LabelPosition;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelConfig;
import com.sencha.gxt.chart.client.draw.Gradient;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.Stop;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextAnchor;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextBaseline;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;

public class SampleEntryPoint implements EntryPoint {

	private EmployeeProperties props = GWT.create(EmployeeProperties.class);
	
	@Override
	public void onModuleLoad() {
		ListStore<Employee> employeeStore = createEmployeeStore();
		HorizontalLayoutContainer chartArea = new HorizontalLayoutContainer();
	    chartArea.add(createBarChart(employeeStore));
	    chartArea.add(createPieChart(employeeStore));
	    
	    RootPanel.get().add(chartArea);
	}
	
	private ListStore<Employee> createEmployeeStore(){
		ListStore<Employee> store = new ListStore<Employee>(props.nameKey());
		store.add(new Employee("Sam",23));
		store.add(new Employee("Ram",17));
		store.add(new Employee("John",7));
		return store;
	}
	
	private Chart<Employee> createPieChart(ListStore<Employee> employeeStore){
		Chart<Employee> chart = new Chart<Employee>();
		chart.setDefaultInsets(50);
		chart.setStore(employeeStore);
		chart.setShadowChart(true);
		Gradient slice1 = new Gradient("slice1", 45);
	    slice1.addStop(new Stop(0, new RGB(148, 174, 10)));
	    slice1.addStop(new Stop(100, new RGB(107, 126, 7)));
	    chart.addGradient(slice1);
	 
	    Gradient slice2 = new Gradient("slice2", 45);
	    slice2.addStop(new Stop(0, new RGB(17, 95, 166)));
	    slice2.addStop(new Stop(100, new RGB(12, 69, 120)));
	    chart.addGradient(slice2);
	 
	    Gradient slice3 = new Gradient("slice3", 45);
	    slice3.addStop(new Stop(0, new RGB(166, 17, 32)));
	    slice3.addStop(new Stop(100, new RGB(120, 12, 23)));
	    chart.addGradient(slice3);
	    
	    final PieSeries<Employee> series = new PieSeries<Employee>();
	    series.setAngleField(props.yearsOfExperience());
	    series.addColor(slice1);
	    series.addColor(slice2);
	    series.addColor(slice3);
	    
	    TextSprite textConfig = new TextSprite();
	    textConfig.setFont("Arial");
	    textConfig.setTextBaseline(TextBaseline.MIDDLE);
	    textConfig.setFontSize(12);
	    textConfig.setTextAnchor(TextAnchor.MIDDLE);
	    textConfig.setZIndex(15);
	    SeriesLabelConfig<Employee> labelConfig = new SeriesLabelConfig<Employee>();
	    labelConfig.setSpriteConfig(textConfig);
	    labelConfig.setLabelPosition(LabelPosition.START);
	    labelConfig.setValueProvider(props.name(), new StringLabelProvider<String>());
	    series.setLabelConfig(labelConfig);
	    series.setHighlighting(true);
	    chart.addSeries(series);
	    series.setLabelConfig(labelConfig);
	    series.setHighlighting(true);
	    
	    chart.setLayoutData(new HorizontalLayoutContainer.HorizontalLayoutData(500, 400, new Margins(1)));
	    
		return chart;
	}
	
	private Chart<Employee> createBarChart(ListStore<Employee> employeeStore){
		Chart<Employee> chart = new Chart<Employee>();
		chart.setDefaultInsets(50);
		chart.setStore(employeeStore);
		chart.setShadowChart(true);

		NumericAxis<Employee> axis = new NumericAxis<Employee>();
	    axis.setPosition(Position.BOTTOM);
	    axis.addField(props.yearsOfExperience());
	    TextSprite title = new TextSprite("Number of years of experience");
	    title.setFontSize(18);
	    axis.setTitleConfig(title);
	    axis.setDisplayGrid(true);
	    axis.setMinimum(0);
	    axis.setMaximum(30);
	    chart.addAxis(axis);
	 
	    CategoryAxis<Employee, String> catAxis = new CategoryAxis<Employee, String>();
	    catAxis.setPosition(Position.LEFT);
	    catAxis.setField(props.name());
	    title = new TextSprite("Employee");
	    title.setFontSize(18);
	    catAxis.setTitleConfig(title);
	    chart.addAxis(catAxis);
	 
	    final BarSeries<Employee> bar = new BarSeries<Employee>();
	    bar.setYAxisPosition(Position.BOTTOM);
	    bar.addYField(props.yearsOfExperience());
	    bar.addColor(new RGB(148,174,10));
	    bar.setHighlighting(true);
	    chart.addSeries(bar);
	    
	    chart.setLayoutData(new HorizontalLayoutContainer.HorizontalLayoutData(500, 400, new Margins(1)));
	    return chart;
	}

}
