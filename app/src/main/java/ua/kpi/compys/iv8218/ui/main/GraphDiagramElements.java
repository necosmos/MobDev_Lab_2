package ua.kpi.compys.iv8218.ui.main;
import ua.kpi.compys.iv8218.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.ArrayList;
import java.util.Objects;
import static android.view.View.INVISIBLE;

public class GraphDiagramElements extends Fragment {
    private LineChart lineChart;
    private PieChart pieChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_second, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Switch Switch = view.findViewById(R.id.graphSwitch);
        Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                pieChart.setVisibility(View.VISIBLE);
                lineChart.setVisibility(INVISIBLE);
            } else {
                lineChart.setVisibility(View.VISIBLE);
                pieChart.setVisibility(INVISIBLE);
            }
        });
        initialiseLineFragment();
        initialisePieFragment();
    }

    private void initialiseLineFragment() {
        lineChart = Objects.requireNonNull(getView()).findViewById(R.id.graphPlot);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(true);
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setDrawAxisLine(true);
        yAxisLeft.setDrawGridLines(true);
        yAxisLeft.setDrawZeroLine(true);
        ArrayList<Entry> dataSet = new ArrayList<>();
        for (float x =-5; x < 5; x += 0.01f) {
            dataSet.add(new Entry(x, (float) x*x));
        }
        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(new LineDataSet(dataSet, "y = x^2"));
        LineData lineData = new LineData(iLineDataSets);
        Description desc = new Description();
        lineChart.setDescription(desc);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    private void initialisePieFragment() {
        ArrayList<PieEntry> dataSet;
        dataSet = new ArrayList<>();
        dataSet.add(new PieEntry(35, 35));
        dataSet.add(new PieEntry(40, 40));
        dataSet.add(new PieEntry(25, 25));

        ArrayList<Integer> colors;
        colors = new ArrayList<>();
        colors.add(Color.parseColor("#009600"));
        colors.add(Color.parseColor("#bfbf02"));
        colors.add(Color.parseColor("#960000"));
        PieDataSet pieDataSet = new PieDataSet(dataSet,"var5");
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieChart = Objects.requireNonNull(getView()).findViewById(R.id.diagramPlot);
        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.getDescription().setEnabled(false);
        pieChart.animate();
        pieChart.setVisibility(INVISIBLE);
    }
}
