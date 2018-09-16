<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class Form5
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()>
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        Dim ChartArea1 As System.Windows.Forms.DataVisualization.Charting.ChartArea = New System.Windows.Forms.DataVisualization.Charting.ChartArea()
        Dim Legend1 As System.Windows.Forms.DataVisualization.Charting.Legend = New System.Windows.Forms.DataVisualization.Charting.Legend()
        Dim Series1 As System.Windows.Forms.DataVisualization.Charting.Series = New System.Windows.Forms.DataVisualization.Charting.Series()
        Dim DataPoint1 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 1.0R)
        Dim DataPoint2 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 12.0R)
        Dim DataPoint3 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 4.0R)
        Dim DataPoint4 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 6.0R)
        Dim DataPoint5 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 2.0R)
        Dim DataPoint6 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 12.0R)
        Dim DataPoint7 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 0R)
        Dim DataPoint8 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 0R)
        Dim DataPoint9 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 5.0R)
        Dim DataPoint10 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 0R)
        Dim DataPoint11 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 3.0R)
        Dim DataPoint12 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 0R)
        Dim Title1 As System.Windows.Forms.DataVisualization.Charting.Title = New System.Windows.Forms.DataVisualization.Charting.Title()
        Dim ChartArea2 As System.Windows.Forms.DataVisualization.Charting.ChartArea = New System.Windows.Forms.DataVisualization.Charting.ChartArea()
        Dim Legend2 As System.Windows.Forms.DataVisualization.Charting.Legend = New System.Windows.Forms.DataVisualization.Charting.Legend()
        Dim Series2 As System.Windows.Forms.DataVisualization.Charting.Series = New System.Windows.Forms.DataVisualization.Charting.Series()
        Dim DataPoint13 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 0.14R)
        Dim DataPoint14 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 0.06R)
        Dim DataPoint15 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 0.15R)
        Dim DataPoint16 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 0.58R)
        Dim DataPoint17 As System.Windows.Forms.DataVisualization.Charting.DataPoint = New System.Windows.Forms.DataVisualization.Charting.DataPoint(0R, 0.07R)
        Dim Title2 As System.Windows.Forms.DataVisualization.Charting.Title = New System.Windows.Forms.DataVisualization.Charting.Title()
        Me.Bugs = New System.Windows.Forms.TabControl()
        Me.TabPage1 = New System.Windows.Forms.TabPage()
        Me.Chart1 = New System.Windows.Forms.DataVisualization.Charting.Chart()
        Me.TabPage2 = New System.Windows.Forms.TabPage()
        Me.Chart2 = New System.Windows.Forms.DataVisualization.Charting.Chart()
        Me.Bugs.SuspendLayout()
        Me.TabPage1.SuspendLayout()
        CType(Me.Chart1, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.TabPage2.SuspendLayout()
        CType(Me.Chart2, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'Bugs
        '
        Me.Bugs.Controls.Add(Me.TabPage1)
        Me.Bugs.Controls.Add(Me.TabPage2)
        Me.Bugs.Location = New System.Drawing.Point(36, 13)
        Me.Bugs.Name = "Bugs"
        Me.Bugs.SelectedIndex = 0
        Me.Bugs.Size = New System.Drawing.Size(1102, 597)
        Me.Bugs.TabIndex = 0
        '
        'TabPage1
        '
        Me.TabPage1.Controls.Add(Me.Chart1)
        Me.TabPage1.Location = New System.Drawing.Point(4, 29)
        Me.TabPage1.Name = "TabPage1"
        Me.TabPage1.Padding = New System.Windows.Forms.Padding(3)
        Me.TabPage1.Size = New System.Drawing.Size(1094, 564)
        Me.TabPage1.TabIndex = 0
        Me.TabPage1.Text = "Bug Distribution"
        Me.TabPage1.UseVisualStyleBackColor = True
        '
        'Chart1
        '
        ChartArea1.AxisX.Interval = 1.0R
        ChartArea1.AxisX.IntervalAutoMode = System.Windows.Forms.DataVisualization.Charting.IntervalAutoMode.VariableCount
        ChartArea1.AxisX.IntervalOffsetType = System.Windows.Forms.DataVisualization.Charting.DateTimeIntervalType.Number
        ChartArea1.AxisX.IntervalType = System.Windows.Forms.DataVisualization.Charting.DateTimeIntervalType.Number
        ChartArea1.AxisX.IsLabelAutoFit = False
        ChartArea1.AxisX.MajorGrid.Enabled = False
        ChartArea1.AxisX.MajorTickMark.Enabled = False
        ChartArea1.AxisY.MajorGrid.Enabled = False
        ChartArea1.Name = "ChartArea2"
        Me.Chart1.ChartAreas.Add(ChartArea1)
        Legend1.Name = "Legend1"
        Me.Chart1.Legends.Add(Legend1)
        Me.Chart1.Location = New System.Drawing.Point(27, 32)
        Me.Chart1.Name = "Chart1"
        Series1.ChartArea = "ChartArea2"
        Series1.CustomProperties = "LabelStyle=Center"
        Series1.EmptyPointStyle.BackGradientStyle = System.Windows.Forms.DataVisualization.Charting.GradientStyle.LeftRight
        Series1.EmptyPointStyle.Color = System.Drawing.Color.FromArgb(CType(CType(192, Byte), Integer), CType(CType(192, Byte), Integer), CType(CType(255, Byte), Integer))
        Series1.EmptyPointStyle.CustomProperties = "DrawingStyle=LightToDark"
        Series1.EmptyPointStyle.IsValueShownAsLabel = True
        Series1.EmptyPointStyle.MarkerColor = System.Drawing.Color.Blue
        Series1.IsXValueIndexed = True
        Series1.Legend = "Legend1"
        Series1.Name = "Bugs Found"
        DataPoint1.AxisLabel = "j_cmr"
        DataPoint2.AxisLabel = "j_ecs"
        DataPoint2.CustomProperties = "LabelStyle=Center"
        DataPoint3.AxisLabel = "j_aiv"
        DataPoint3.CustomProperties = "LabelStyle=Center"
        DataPoint4.AxisLabel = "j_irv"
        DataPoint5.AxisLabel = "j_ioi"
        DataPoint6.AxisLabel = "j_idN"
        DataPoint7.AxisLabel = "j_abc"
        DataPoint8.AxisLabel = "j_wabc"
        DataPoint9.AxisLabel = "c_avc"
        DataPoint10.AxisLabel = "c_did"
        DataPoint11.AxisLabel = "c_irv"
        DataPoint12.AxisLabel = "c_wavc"
        Series1.Points.Add(DataPoint1)
        Series1.Points.Add(DataPoint2)
        Series1.Points.Add(DataPoint3)
        Series1.Points.Add(DataPoint4)
        Series1.Points.Add(DataPoint5)
        Series1.Points.Add(DataPoint6)
        Series1.Points.Add(DataPoint7)
        Series1.Points.Add(DataPoint8)
        Series1.Points.Add(DataPoint9)
        Series1.Points.Add(DataPoint10)
        Series1.Points.Add(DataPoint11)
        Series1.Points.Add(DataPoint12)
        Series1.XValueType = System.Windows.Forms.DataVisualization.Charting.ChartValueType.[String]
        Series1.YValueType = System.Windows.Forms.DataVisualization.Charting.ChartValueType.Int32
        Me.Chart1.Series.Add(Series1)
        Me.Chart1.Size = New System.Drawing.Size(1049, 498)
        Me.Chart1.TabIndex = 0
        Me.Chart1.Text = "Chart1"
        Title1.Name = "Bug Distribution"
        Me.Chart1.Titles.Add(Title1)
        '
        'TabPage2
        '
        Me.TabPage2.Controls.Add(Me.Chart2)
        Me.TabPage2.Location = New System.Drawing.Point(4, 29)
        Me.TabPage2.Name = "TabPage2"
        Me.TabPage2.Padding = New System.Windows.Forms.Padding(3)
        Me.TabPage2.Size = New System.Drawing.Size(1094, 564)
        Me.TabPage2.TabIndex = 1
        Me.TabPage2.Text = "Language Distribution"
        Me.TabPage2.UseVisualStyleBackColor = True
        '
        'Chart2
        '
        ChartArea2.Name = "ChartArea1"
        Me.Chart2.ChartAreas.Add(ChartArea2)
        Legend2.Name = "Legend1"
        Me.Chart2.Legends.Add(Legend2)
        Me.Chart2.Location = New System.Drawing.Point(77, 52)
        Me.Chart2.Name = "Chart2"
        Me.Chart2.Palette = System.Windows.Forms.DataVisualization.Charting.ChartColorPalette.None
        Me.Chart2.PaletteCustomColors = New System.Drawing.Color() {System.Drawing.Color.LightSteelBlue, System.Drawing.Color.LightBlue, System.Drawing.Color.SkyBlue, System.Drawing.Color.CornflowerBlue, System.Drawing.Color.DodgerBlue}
        Series2.ChartArea = "ChartArea1"
        Series2.ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Pie
        Series2.IsValueShownAsLabel = True
        Series2.LabelFormat = "#%"
        Series2.Legend = "Legend1"
        Series2.Name = "Series1"
        DataPoint13.LegendText = "Java"
        DataPoint14.LegendText = "C"
        DataPoint15.LegendText = "C++"
        DataPoint16.LegendText = "Unsupported"
        DataPoint17.LegendText = "Unsupported with Supported Files"
        Series2.Points.Add(DataPoint13)
        Series2.Points.Add(DataPoint14)
        Series2.Points.Add(DataPoint15)
        Series2.Points.Add(DataPoint16)
        Series2.Points.Add(DataPoint17)
        Series2.XValueType = System.Windows.Forms.DataVisualization.Charting.ChartValueType.[String]
        Me.Chart2.Series.Add(Series2)
        Me.Chart2.Size = New System.Drawing.Size(951, 467)
        Me.Chart2.TabIndex = 0
        Me.Chart2.Text = "Chart2"
        Title2.Name = "Language Distribution"
        Me.Chart2.Titles.Add(Title2)
        '
        'Form5
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(9.0!, 20.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(1168, 636)
        Me.Controls.Add(Me.Bugs)
        Me.Name = "Form5"
        Me.Text = "Graphs"
        Me.Bugs.ResumeLayout(False)
        Me.TabPage1.ResumeLayout(False)
        CType(Me.Chart1, System.ComponentModel.ISupportInitialize).EndInit()
        Me.TabPage2.ResumeLayout(False)
        CType(Me.Chart2, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

    Friend WithEvents Bugs As TabControl
    Friend WithEvents TabPage1 As TabPage
    Friend WithEvents TabPage2 As TabPage
    Friend WithEvents Chart1 As DataVisualization.Charting.Chart
    Friend WithEvents Chart2 As DataVisualization.Charting.Chart
End Class
