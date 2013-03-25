package layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.gpaontherun.R;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class BarGraph extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graphs);

		// init example series data
		GraphViewSeries exampleSeries = new GraphViewSeries(
				new GraphViewData[] {
						new GraphViewData(1, 2.0d),
						new GraphViewData(2, 1.5d),
						//new GraphViewData(2.5, 3.0d) // another frequency
						new GraphViewData(3, 2.5d),
						new GraphViewData(4, 1.0d), new GraphViewData(5, 3.0d) });

		// graph with dynamically genereated horizontal and vertical labels
		GraphView graphView;
		if (getIntent().getStringExtra("type").equals("bar")) {
			graphView = new BarGraphView(this // context
					, "GraphViewDemo" // heading
			);
		} else {
			graphView = new LineGraphView(this // context
					, "GraphViewDemo" // heading
			);
		}
		graphView.setHorizontalLabels(new String[] {"A", "B", "C", "D", "E", "F"});
		graphView.addSeries(exampleSeries); // data

		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
		layout.addView(graphView);
	}
}