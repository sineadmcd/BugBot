<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form1
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
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
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.StatGroupBox = New System.Windows.Forms.GroupBox()
        Me.StatVal5 = New System.Windows.Forms.Label()
        Me.StatVal4 = New System.Windows.Forms.Label()
        Me.StatVal3 = New System.Windows.Forms.Label()
        Me.StatVal2 = New System.Windows.Forms.Label()
        Me.StatVal1 = New System.Windows.Forms.Label()
        Me.StatLabel5 = New System.Windows.Forms.Label()
        Me.StatLabel4 = New System.Windows.Forms.Label()
        Me.StatLabel3 = New System.Windows.Forms.Label()
        Me.StatLabel2 = New System.Windows.Forms.Label()
        Me.StatLabel1 = New System.Windows.Forms.Label()
        Me.StatusLabel = New System.Windows.Forms.Label()
        Me.RepoName = New System.Windows.Forms.Label()
        Me.StatusTrackBar = New System.Windows.Forms.TrackBar()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.Label7 = New System.Windows.Forms.Label()
        Me.Label8 = New System.Windows.Forms.Label()
        Me.RefreshButton = New System.Windows.Forms.Button()
        Me.ResetButton = New System.Windows.Forms.Button()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.qlengthLabel = New System.Windows.Forms.Label()
        Me.BugHiveButton = New System.Windows.Forms.Button()
        Me.TwitterFeed = New System.Windows.Forms.WebBrowser()
        Me.Label9 = New System.Windows.Forms.Label()
        Me.Button1 = New System.Windows.Forms.Button()
        Me.Graphs = New System.Windows.Forms.Button()
        Me.StatGroupBox.SuspendLayout()
        CType(Me.StatusTrackBar, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'StatGroupBox
        '
        Me.StatGroupBox.Controls.Add(Me.StatVal5)
        Me.StatGroupBox.Controls.Add(Me.StatVal4)
        Me.StatGroupBox.Controls.Add(Me.StatVal3)
        Me.StatGroupBox.Controls.Add(Me.StatVal2)
        Me.StatGroupBox.Controls.Add(Me.StatVal1)
        Me.StatGroupBox.Controls.Add(Me.StatLabel5)
        Me.StatGroupBox.Controls.Add(Me.StatLabel4)
        Me.StatGroupBox.Controls.Add(Me.StatLabel3)
        Me.StatGroupBox.Controls.Add(Me.StatLabel2)
        Me.StatGroupBox.Controls.Add(Me.StatLabel1)
        Me.StatGroupBox.Location = New System.Drawing.Point(440, 378)
        Me.StatGroupBox.Name = "StatGroupBox"
        Me.StatGroupBox.Size = New System.Drawing.Size(344, 278)
        Me.StatGroupBox.TabIndex = 0
        Me.StatGroupBox.TabStop = False
        Me.StatGroupBox.Text = "Statistics"
        '
        'StatVal5
        '
        Me.StatVal5.AutoSize = True
        Me.StatVal5.Location = New System.Drawing.Point(267, 224)
        Me.StatVal5.Name = "StatVal5"
        Me.StatVal5.Size = New System.Drawing.Size(18, 20)
        Me.StatVal5.TabIndex = 9
        Me.StatVal5.Text = "0"
        '
        'StatVal4
        '
        Me.StatVal4.AutoSize = True
        Me.StatVal4.Location = New System.Drawing.Point(267, 180)
        Me.StatVal4.Name = "StatVal4"
        Me.StatVal4.Size = New System.Drawing.Size(18, 20)
        Me.StatVal4.TabIndex = 8
        Me.StatVal4.Text = "0"
        '
        'StatVal3
        '
        Me.StatVal3.AutoSize = True
        Me.StatVal3.Location = New System.Drawing.Point(267, 136)
        Me.StatVal3.Name = "StatVal3"
        Me.StatVal3.Size = New System.Drawing.Size(18, 20)
        Me.StatVal3.TabIndex = 7
        Me.StatVal3.Text = "0"
        '
        'StatVal2
        '
        Me.StatVal2.AutoSize = True
        Me.StatVal2.Location = New System.Drawing.Point(267, 93)
        Me.StatVal2.Name = "StatVal2"
        Me.StatVal2.Size = New System.Drawing.Size(18, 20)
        Me.StatVal2.TabIndex = 6
        Me.StatVal2.Text = "0"
        '
        'StatVal1
        '
        Me.StatVal1.AutoSize = True
        Me.StatVal1.Location = New System.Drawing.Point(267, 52)
        Me.StatVal1.Name = "StatVal1"
        Me.StatVal1.Size = New System.Drawing.Size(18, 20)
        Me.StatVal1.TabIndex = 5
        Me.StatVal1.Text = "0"
        '
        'StatLabel5
        '
        Me.StatLabel5.AutoSize = True
        Me.StatLabel5.Location = New System.Drawing.Point(17, 224)
        Me.StatLabel5.Name = "StatLabel5"
        Me.StatLabel5.Size = New System.Drawing.Size(130, 20)
        Me.StatLabel5.TabIndex = 4
        Me.StatLabel5.Text = "Bugs in BugHive:"
        '
        'StatLabel4
        '
        Me.StatLabel4.AutoSize = True
        Me.StatLabel4.Location = New System.Drawing.Point(17, 180)
        Me.StatLabel4.Name = "StatLabel4"
        Me.StatLabel4.Size = New System.Drawing.Size(135, 20)
        Me.StatLabel4.TabIndex = 3
        Me.StatLabel4.Text = "Changes Pushed:"
        '
        'StatLabel3
        '
        Me.StatLabel3.AutoSize = True
        Me.StatLabel3.Location = New System.Drawing.Point(17, 136)
        Me.StatLabel3.Name = "StatLabel3"
        Me.StatLabel3.Size = New System.Drawing.Size(120, 20)
        Me.StatLabel3.TabIndex = 2
        Me.StatLabel3.Text = "Bugs Identified:"
        '
        'StatLabel2
        '
        Me.StatLabel2.AutoSize = True
        Me.StatLabel2.Location = New System.Drawing.Point(17, 93)
        Me.StatLabel2.Name = "StatLabel2"
        Me.StatLabel2.Size = New System.Drawing.Size(173, 20)
        Me.StatLabel2.TabIndex = 1
        Me.StatLabel2.Text = "Repo Access Request:"
        '
        'StatLabel1
        '
        Me.StatLabel1.AutoSize = True
        Me.StatLabel1.Location = New System.Drawing.Point(18, 52)
        Me.StatLabel1.Name = "StatLabel1"
        Me.StatLabel1.Size = New System.Drawing.Size(134, 20)
        Me.StatLabel1.TabIndex = 0
        Me.StatLabel1.Text = "Repos Accessed:"
        '
        'StatusLabel
        '
        Me.StatusLabel.AutoSize = True
        Me.StatusLabel.Location = New System.Drawing.Point(38, 265)
        Me.StatusLabel.Name = "StatusLabel"
        Me.StatusLabel.Size = New System.Drawing.Size(172, 20)
        Me.StatusLabel.TabIndex = 1
        Me.StatusLabel.Text = "Caurrently Working On:"
        '
        'RepoName
        '
        Me.RepoName.AutoSize = True
        Me.RepoName.Location = New System.Drawing.Point(216, 265)
        Me.RepoName.Name = "RepoName"
        Me.RepoName.Size = New System.Drawing.Size(64, 20)
        Me.RepoName.TabIndex = 2
        Me.RepoName.Text = "Nothing"
        '
        'StatusTrackBar
        '
        Me.StatusTrackBar.Location = New System.Drawing.Point(46, 325)
        Me.StatusTrackBar.Maximum = 7
        Me.StatusTrackBar.Minimum = 1
        Me.StatusTrackBar.Name = "StatusTrackBar"
        Me.StatusTrackBar.Orientation = System.Windows.Forms.Orientation.Vertical
        Me.StatusTrackBar.Size = New System.Drawing.Size(69, 331)
        Me.StatusTrackBar.TabIndex = 3
        Me.StatusTrackBar.Value = 7
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(125, 336)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(112, 20)
        Me.Label1.TabIndex = 4
        Me.Label1.Text = "Finding Repos"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(121, 623)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(97, 20)
        Me.Label3.TabIndex = 6
        Me.Label3.Text = "Finishing Up"
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(121, 527)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(87, 20)
        Me.Label4.TabIndex = 7
        Me.Label4.Text = "Debugging"
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Location = New System.Drawing.Point(121, 482)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(123, 20)
        Me.Label5.TabIndex = 8
        Me.Label5.Text = "Identifying Bugs"
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.Location = New System.Drawing.Point(125, 388)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(62, 20)
        Me.Label6.TabIndex = 9
        Me.Label6.Text = "Cloning"
        '
        'Label7
        '
        Me.Label7.AutoSize = True
        Me.Label7.Location = New System.Drawing.Point(125, 430)
        Me.Label7.Name = "Label7"
        Me.Label7.Size = New System.Drawing.Size(76, 20)
        Me.Label7.TabIndex = 10
        Me.Label7.Text = "Scanning"
        '
        'Label8
        '
        Me.Label8.AutoSize = True
        Me.Label8.Location = New System.Drawing.Point(121, 578)
        Me.Label8.Name = "Label8"
        Me.Label8.Size = New System.Drawing.Size(66, 20)
        Me.Label8.TabIndex = 11
        Me.Label8.Text = "Pushing"
        '
        'RefreshButton
        '
        Me.RefreshButton.Location = New System.Drawing.Point(46, 104)
        Me.RefreshButton.Name = "RefreshButton"
        Me.RefreshButton.Size = New System.Drawing.Size(307, 48)
        Me.RefreshButton.TabIndex = 12
        Me.RefreshButton.Text = "Refresh Statistics"
        Me.RefreshButton.UseVisualStyleBackColor = True
        '
        'ResetButton
        '
        Me.ResetButton.BackColor = System.Drawing.Color.LightCoral
        Me.ResetButton.Location = New System.Drawing.Point(440, 120)
        Me.ResetButton.Name = "ResetButton"
        Me.ResetButton.Size = New System.Drawing.Size(307, 32)
        Me.ResetButton.TabIndex = 13
        Me.ResetButton.Text = "RESET BUGBOT"
        Me.ResetButton.UseVisualStyleBackColor = False
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(38, 229)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(115, 20)
        Me.Label2.TabIndex = 14
        Me.Label2.Text = "Queue Length:"
        '
        'qlengthLabel
        '
        Me.qlengthLabel.AutoSize = True
        Me.qlengthLabel.Location = New System.Drawing.Point(161, 229)
        Me.qlengthLabel.Name = "qlengthLabel"
        Me.qlengthLabel.Size = New System.Drawing.Size(61, 20)
        Me.qlengthLabel.TabIndex = 15
        Me.qlengthLabel.Text = "min_10"
        '
        'BugHiveButton
        '
        Me.BugHiveButton.BackColor = System.Drawing.SystemColors.Control
        Me.BugHiveButton.Location = New System.Drawing.Point(46, 38)
        Me.BugHiveButton.Name = "BugHiveButton"
        Me.BugHiveButton.Size = New System.Drawing.Size(307, 48)
        Me.BugHiveButton.TabIndex = 16
        Me.BugHiveButton.Text = "BugHive"
        Me.BugHiveButton.UseVisualStyleBackColor = False
        '
        'TwitterFeed
        '
        Me.TwitterFeed.Location = New System.Drawing.Point(811, 78)
        Me.TwitterFeed.MinimumSize = New System.Drawing.Size(20, 20)
        Me.TwitterFeed.Name = "TwitterFeed"
        Me.TwitterFeed.Size = New System.Drawing.Size(349, 578)
        Me.TwitterFeed.TabIndex = 17
        Me.TwitterFeed.Url = New System.Uri("https://twitter.com/BugBot16", System.UriKind.Absolute)
        '
        'Label9
        '
        Me.Label9.AutoSize = True
        Me.Label9.Location = New System.Drawing.Point(807, 38)
        Me.Label9.Name = "Label9"
        Me.Label9.Size = New System.Drawing.Size(152, 20)
        Me.Label9.TabIndex = 18
        Me.Label9.Text = "Twitter: @BugBot16"
        '
        'Button1
        '
        Me.Button1.Location = New System.Drawing.Point(440, 38)
        Me.Button1.Name = "Button1"
        Me.Button1.Size = New System.Drawing.Size(307, 76)
        Me.Button1.TabIndex = 19
        Me.Button1.Text = "Run BUGBOT"
        Me.Button1.UseVisualStyleBackColor = True
        '
        'Graphs
        '
        Me.Graphs.BackColor = System.Drawing.SystemColors.Control
        Me.Graphs.Location = New System.Drawing.Point(440, 323)
        Me.Graphs.Name = "Graphs"
        Me.Graphs.Size = New System.Drawing.Size(344, 47)
        Me.Graphs.TabIndex = 20
        Me.Graphs.Text = "Graphs"
        Me.Graphs.UseVisualStyleBackColor = False
        '
        'Form1
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(9.0!, 20.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(1183, 693)
        Me.Controls.Add(Me.Graphs)
        Me.Controls.Add(Me.Button1)
        Me.Controls.Add(Me.Label9)
        Me.Controls.Add(Me.TwitterFeed)
        Me.Controls.Add(Me.BugHiveButton)
        Me.Controls.Add(Me.qlengthLabel)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.ResetButton)
        Me.Controls.Add(Me.RefreshButton)
        Me.Controls.Add(Me.Label8)
        Me.Controls.Add(Me.Label7)
        Me.Controls.Add(Me.Label6)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.StatusTrackBar)
        Me.Controls.Add(Me.RepoName)
        Me.Controls.Add(Me.StatusLabel)
        Me.Controls.Add(Me.StatGroupBox)
        Me.Name = "Form1"
        Me.Text = "BUGBOT"
        Me.StatGroupBox.ResumeLayout(False)
        Me.StatGroupBox.PerformLayout()
        CType(Me.StatusTrackBar, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents StatGroupBox As GroupBox
    Friend WithEvents StatLabel5 As Label
    Friend WithEvents StatLabel4 As Label
    Friend WithEvents StatLabel3 As Label
    Friend WithEvents StatLabel2 As Label
    Friend WithEvents StatLabel1 As Label
    Friend WithEvents StatVal5 As Label
    Friend WithEvents StatVal4 As Label
    Friend WithEvents StatVal3 As Label
    Friend WithEvents StatVal2 As Label
    Friend WithEvents StatVal1 As Label
    Friend WithEvents StatusLabel As Label
    Friend WithEvents RepoName As Label
    Friend WithEvents StatusTrackBar As TrackBar
    Friend WithEvents Label1 As Label
    Friend WithEvents Label3 As Label
    Friend WithEvents Label4 As Label
    Friend WithEvents Label5 As Label
    Friend WithEvents Label6 As Label
    Friend WithEvents Label7 As Label
    Friend WithEvents Label8 As Label
    Friend WithEvents RefreshButton As Button
    Friend WithEvents ResetButton As Button
    Friend WithEvents Label2 As Label
    Friend WithEvents qlengthLabel As Label
    Friend WithEvents BugHiveButton As Button
    Friend WithEvents TwitterFeed As WebBrowser
    Friend WithEvents Label9 As Label
    Friend WithEvents Button1 As Button
    Friend WithEvents Graphs As Button
End Class
