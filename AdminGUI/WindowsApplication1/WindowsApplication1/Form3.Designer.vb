<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class Form3
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
        Me.Label1 = New System.Windows.Forms.Label()
        Me.bugidTB = New System.Windows.Forms.TextBox()
        Me.languageTB = New System.Windows.Forms.TextBox()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.Label7 = New System.Windows.Forms.Label()
        Me.Label8 = New System.Windows.Forms.Label()
        Me.Label9 = New System.Windows.Forms.Label()
        Me.Label10 = New System.Windows.Forms.Label()
        Me.Label11 = New System.Windows.Forms.Label()
        Me.Label12 = New System.Windows.Forms.Label()
        Me.Label13 = New System.Windows.Forms.Label()
        Me.variabledependantCB = New System.Windows.Forms.CheckBox()
        Me.variationsCB = New System.Windows.Forms.CheckBox()
        Me.generalstringTB = New System.Windows.Forms.TextBox()
        Me.regexTB = New System.Windows.Forms.TextBox()
        Me.descriptionTB = New System.Windows.Forms.TextBox()
        Me.wantedcasesTB = New System.Windows.Forms.TextBox()
        Me.extendedexplanationTB = New System.Windows.Forms.TextBox()
        Me.solutionTB = New System.Windows.Forms.TextBox()
        Me.fixTB = New System.Windows.Forms.TextBox()
        Me.commitTB = New System.Windows.Forms.TextBox()
        Me.ADD = New System.Windows.Forms.Button()
        Me.EDIT = New System.Windows.Forms.Button()
        Me.DELETE = New System.Windows.Forms.Button()
        Me.NumericUpDown1 = New System.Windows.Forms.NumericUpDown()
        CType(Me.NumericUpDown1, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(39, 44)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(75, 20)
        Me.Label1.TabIndex = 0
        Me.Label1.Text = "BUG_ID:"
        '
        'bugidTB
        '
        Me.bugidTB.Location = New System.Drawing.Point(120, 41)
        Me.bugidTB.Name = "bugidTB"
        Me.bugidTB.Size = New System.Drawing.Size(137, 26)
        Me.bugidTB.TabIndex = 1
        '
        'languageTB
        '
        Me.languageTB.Location = New System.Drawing.Point(383, 40)
        Me.languageTB.Name = "languageTB"
        Me.languageTB.Size = New System.Drawing.Size(100, 26)
        Me.languageTB.TabIndex = 2
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(292, 44)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(85, 20)
        Me.Label2.TabIndex = 3
        Me.Label2.Text = "Language:"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(736, 43)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(155, 20)
        Me.Label3.TabIndex = 4
        Me.Label3.Text = "Variable Dependant:"
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(941, 44)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(84, 20)
        Me.Label4.TabIndex = 5
        Me.Label4.Text = "Variations:"
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Location = New System.Drawing.Point(39, 107)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(112, 20)
        Me.Label5.TabIndex = 6
        Me.Label5.Text = "General String"
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.Location = New System.Drawing.Point(39, 158)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(59, 20)
        Me.Label6.TabIndex = 7
        Me.Label6.Text = "Regex:"
        '
        'Label7
        '
        Me.Label7.AutoSize = True
        Me.Label7.Location = New System.Drawing.Point(39, 214)
        Me.Label7.Name = "Label7"
        Me.Label7.Size = New System.Drawing.Size(93, 20)
        Me.Label7.TabIndex = 8
        Me.Label7.Text = "Description:"
        '
        'Label8
        '
        Me.Label8.AutoSize = True
        Me.Label8.Location = New System.Drawing.Point(39, 266)
        Me.Label8.Name = "Label8"
        Me.Label8.Size = New System.Drawing.Size(118, 20)
        Me.Label8.TabIndex = 9
        Me.Label8.Text = "Wanted Cases:"
        '
        'Label9
        '
        Me.Label9.AutoSize = True
        Me.Label9.Location = New System.Drawing.Point(39, 320)
        Me.Label9.Name = "Label9"
        Me.Label9.Size = New System.Drawing.Size(168, 20)
        Me.Label9.TabIndex = 10
        Me.Label9.Text = "Extended Explanation:"
        '
        'Label10
        '
        Me.Label10.AutoSize = True
        Me.Label10.Location = New System.Drawing.Point(43, 377)
        Me.Label10.Name = "Label10"
        Me.Label10.Size = New System.Drawing.Size(144, 20)
        Me.Label10.TabIndex = 11
        Me.Label10.Text = "Solution Explained:"
        '
        'Label11
        '
        Me.Label11.AutoSize = True
        Me.Label11.Location = New System.Drawing.Point(43, 430)
        Me.Label11.Name = "Label11"
        Me.Label11.Size = New System.Drawing.Size(33, 20)
        Me.Label11.TabIndex = 12
        Me.Label11.Text = "Fix:"
        '
        'Label12
        '
        Me.Label12.AutoSize = True
        Me.Label12.Location = New System.Drawing.Point(43, 479)
        Me.Label12.Name = "Label12"
        Me.Label12.Size = New System.Drawing.Size(136, 20)
        Me.Label12.TabIndex = 13
        Me.Label12.Text = "Commit Message:"
        '
        'Label13
        '
        Me.Label13.AutoSize = True
        Me.Label13.Location = New System.Drawing.Point(520, 43)
        Me.Label13.Name = "Label13"
        Me.Label13.Size = New System.Drawing.Size(69, 20)
        Me.Label13.TabIndex = 14
        Me.Label13.Text = "Number:"
        '
        'variabledependantCB
        '
        Me.variabledependantCB.AutoSize = True
        Me.variabledependantCB.Location = New System.Drawing.Point(897, 43)
        Me.variabledependantCB.Name = "variabledependantCB"
        Me.variabledependantCB.Size = New System.Drawing.Size(22, 21)
        Me.variabledependantCB.TabIndex = 16
        Me.variabledependantCB.UseVisualStyleBackColor = True
        '
        'variationsCB
        '
        Me.variationsCB.AutoSize = True
        Me.variationsCB.Location = New System.Drawing.Point(1031, 44)
        Me.variationsCB.Name = "variationsCB"
        Me.variationsCB.Size = New System.Drawing.Size(22, 21)
        Me.variationsCB.TabIndex = 17
        Me.variationsCB.UseVisualStyleBackColor = True
        '
        'generalstringTB
        '
        Me.generalstringTB.Location = New System.Drawing.Point(230, 107)
        Me.generalstringTB.Name = "generalstringTB"
        Me.generalstringTB.Size = New System.Drawing.Size(917, 26)
        Me.generalstringTB.TabIndex = 18
        '
        'regexTB
        '
        Me.regexTB.Location = New System.Drawing.Point(230, 158)
        Me.regexTB.Name = "regexTB"
        Me.regexTB.Size = New System.Drawing.Size(917, 26)
        Me.regexTB.TabIndex = 19
        '
        'descriptionTB
        '
        Me.descriptionTB.Location = New System.Drawing.Point(230, 208)
        Me.descriptionTB.Name = "descriptionTB"
        Me.descriptionTB.Size = New System.Drawing.Size(917, 26)
        Me.descriptionTB.TabIndex = 20
        '
        'wantedcasesTB
        '
        Me.wantedcasesTB.Location = New System.Drawing.Point(230, 263)
        Me.wantedcasesTB.Name = "wantedcasesTB"
        Me.wantedcasesTB.Size = New System.Drawing.Size(917, 26)
        Me.wantedcasesTB.TabIndex = 21
        '
        'extendedexplanationTB
        '
        Me.extendedexplanationTB.Location = New System.Drawing.Point(230, 314)
        Me.extendedexplanationTB.Name = "extendedexplanationTB"
        Me.extendedexplanationTB.Size = New System.Drawing.Size(917, 26)
        Me.extendedexplanationTB.TabIndex = 22
        '
        'solutionTB
        '
        Me.solutionTB.Location = New System.Drawing.Point(230, 371)
        Me.solutionTB.Name = "solutionTB"
        Me.solutionTB.Size = New System.Drawing.Size(917, 26)
        Me.solutionTB.TabIndex = 23
        '
        'fixTB
        '
        Me.fixTB.Location = New System.Drawing.Point(230, 430)
        Me.fixTB.Name = "fixTB"
        Me.fixTB.Size = New System.Drawing.Size(917, 26)
        Me.fixTB.TabIndex = 24
        '
        'commitTB
        '
        Me.commitTB.Location = New System.Drawing.Point(230, 476)
        Me.commitTB.Name = "commitTB"
        Me.commitTB.Size = New System.Drawing.Size(917, 26)
        Me.commitTB.TabIndex = 25
        '
        'ADD
        '
        Me.ADD.Location = New System.Drawing.Point(230, 522)
        Me.ADD.Name = "ADD"
        Me.ADD.Size = New System.Drawing.Size(294, 38)
        Me.ADD.TabIndex = 26
        Me.ADD.Text = "ADD"
        Me.ADD.UseVisualStyleBackColor = True
        '
        'EDIT
        '
        Me.EDIT.Location = New System.Drawing.Point(541, 522)
        Me.EDIT.Name = "EDIT"
        Me.EDIT.Size = New System.Drawing.Size(294, 38)
        Me.EDIT.TabIndex = 27
        Me.EDIT.Text = "EDIT"
        Me.EDIT.UseVisualStyleBackColor = True
        '
        'DELETE
        '
        Me.DELETE.Location = New System.Drawing.Point(853, 522)
        Me.DELETE.Name = "DELETE"
        Me.DELETE.Size = New System.Drawing.Size(294, 38)
        Me.DELETE.TabIndex = 28
        Me.DELETE.Text = "DELETE"
        Me.DELETE.UseVisualStyleBackColor = True
        '
        'NumericUpDown1
        '
        Me.NumericUpDown1.Location = New System.Drawing.Point(595, 40)
        Me.NumericUpDown1.Name = "NumericUpDown1"
        Me.NumericUpDown1.Size = New System.Drawing.Size(120, 26)
        Me.NumericUpDown1.TabIndex = 29
        '
        'Form3
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(9.0!, 20.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(1197, 572)
        Me.Controls.Add(Me.NumericUpDown1)
        Me.Controls.Add(Me.DELETE)
        Me.Controls.Add(Me.EDIT)
        Me.Controls.Add(Me.ADD)
        Me.Controls.Add(Me.commitTB)
        Me.Controls.Add(Me.fixTB)
        Me.Controls.Add(Me.solutionTB)
        Me.Controls.Add(Me.extendedexplanationTB)
        Me.Controls.Add(Me.wantedcasesTB)
        Me.Controls.Add(Me.descriptionTB)
        Me.Controls.Add(Me.regexTB)
        Me.Controls.Add(Me.generalstringTB)
        Me.Controls.Add(Me.variationsCB)
        Me.Controls.Add(Me.variabledependantCB)
        Me.Controls.Add(Me.Label13)
        Me.Controls.Add(Me.Label12)
        Me.Controls.Add(Me.Label11)
        Me.Controls.Add(Me.Label10)
        Me.Controls.Add(Me.Label9)
        Me.Controls.Add(Me.Label8)
        Me.Controls.Add(Me.Label7)
        Me.Controls.Add(Me.Label6)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.languageTB)
        Me.Controls.Add(Me.bugidTB)
        Me.Controls.Add(Me.Label1)
        Me.Name = "Form3"
        Me.Text = "BugHive"
        CType(Me.NumericUpDown1, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents Label1 As Label
    Friend WithEvents bugidTB As TextBox
    Friend WithEvents languageTB As TextBox
    Friend WithEvents Label2 As Label
    Friend WithEvents Label3 As Label
    Friend WithEvents Label4 As Label
    Friend WithEvents Label5 As Label
    Friend WithEvents Label6 As Label
    Friend WithEvents Label7 As Label
    Friend WithEvents Label8 As Label
    Friend WithEvents Label9 As Label
    Friend WithEvents Label10 As Label
    Friend WithEvents Label11 As Label
    Friend WithEvents Label12 As Label
    Friend WithEvents Label13 As Label
    Friend WithEvents variabledependantCB As CheckBox
    Friend WithEvents variationsCB As CheckBox
    Friend WithEvents generalstringTB As TextBox
    Friend WithEvents regexTB As TextBox
    Friend WithEvents descriptionTB As TextBox
    Friend WithEvents wantedcasesTB As TextBox
    Friend WithEvents extendedexplanationTB As TextBox
    Friend WithEvents solutionTB As TextBox
    Friend WithEvents fixTB As TextBox
    Friend WithEvents commitTB As TextBox
    Friend WithEvents ADD As Button
    Friend WithEvents EDIT As Button
    Friend WithEvents DELETE As Button
    Friend WithEvents NumericUpDown1 As NumericUpDown
End Class
