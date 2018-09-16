Imports MySql.Data.MySqlClient

Public Class Form3

    Dim myDatabase As MySqlConnection
    Private Sub Form3_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        NumericUpDown1.Value = Form1.numbugs

    End Sub

    Private Sub ADD_Click(sender As Object, e As EventArgs) Handles ADD.Click
        myDatabase = New MySqlConnection("server=danu6.it.nuigalway.ie;user id=mydb2831ms;password=vi3mon;database=mydb2831")

        Dim strQuery As String = "INSERT INTO BugHive(BUG_ID, Language, VariableDependant, Variations, GeneralString, RegularExpression, Description, WantedCases, ExtendedExplanation, SolutionExplained, Fix, CommitMessage, Num) 
                                    VALUES (@BUG_ID, @Language, @VariableDependant, @Variations, @GeneralString, @RegularExpression, @Description, @WantedCases, @ExtendedExplanation, @SolutionExplained, @Fix, @CommitMessage, @Num)"

        Dim sqlCom As MySqlCommand = New MySqlCommand(strQuery, myDatabase)

        With sqlCom
            .Parameters.AddWithValue("@BUG_ID", bugidTB.Text)
            .Parameters.AddWithValue("@Language", languageTB.Text)
            .Parameters.AddWithValue("@VariableDependant", variabledependantCB.Checked)
            .Parameters.AddWithValue("@Variations", variationsCB.Checked)
            .Parameters.AddWithValue("@GeneralString", generalstringTB.Text)
            .Parameters.AddWithValue("@RegularExpression", regexTB.Text)
            .Parameters.AddWithValue("@Description", descriptionTB.Text)
            .Parameters.AddWithValue("@WantedCases", wantedcasesTB.Text)
            .Parameters.AddWithValue("@ExtendedExplanation", extendedexplanationTB.Text)
            .Parameters.AddWithValue("@SolutionExplained", solutionTB.Text)
            .Parameters.AddWithValue("@Fix", fixTB.Text)
            .Parameters.AddWithValue("@CommitMessage", commitTB.Text)
            .Parameters.AddWithValue("@Num", NumericUpDown1.Value)
        End With

        myDatabase.Open()
        sqlCom.ExecuteNonQuery()
        sqlCom.Dispose()
        myDatabase.Close()

    End Sub

    Private Sub EDIT_Click(sender As Object, e As EventArgs) Handles EDIT.Click
        myDatabase = New MySqlConnection("server=danu6.it.nuigalway.ie;user id=mydb2831ms;password=vi3mon;database=mydb2831")

        Dim strQuery As String = "UPDATE BugHive SET BUG_ID=@BUG_ID,Language=@Language,VariableDependant=@VariableDependant,
                                Variations=@Variations,GeneralString=@GeneralString,RegularExpression=@RegularExpression,Description=@Description,
                                WantedCases=@WantedCases,ExtendedExplanation=@ExtendedExplanation,SolutionExplained=@SolutionExplained,Fix=@Fix,
                                CommitMessage=@CommitMessage WHERE Num=@number"

        Dim sqlCom As MySqlCommand = New MySqlCommand(strQuery, myDatabase)

        With sqlCom
            .Parameters.AddWithValue("@BUG_ID", bugidTB.Text)
            .Parameters.AddWithValue("@Language", languageTB.Text)
            .Parameters.AddWithValue("@VariableDependant", variabledependantCB.Checked)
            .Parameters.AddWithValue("@Variations", variationsCB.Checked)
            .Parameters.AddWithValue("@GeneralString", generalstringTB.Text)
            .Parameters.AddWithValue("@RegularExpression", regexTB.Text)
            .Parameters.AddWithValue("@Description", descriptionTB.Text)
            .Parameters.AddWithValue("@WantedCases", wantedcasesTB.Text)
            .Parameters.AddWithValue("@ExtendedExplanation", extendedexplanationTB.Text)
            .Parameters.AddWithValue("@SolutionExplained", solutionTB.Text)
            .Parameters.AddWithValue("@Fix", fixTB.Text)
            .Parameters.AddWithValue("@CommitMessage", commitTB.Text)
            .Parameters.AddWithValue("@number", NumericUpDown1.Value)
        End With

        myDatabase.Open()
        sqlCom.ExecuteNonQuery()
        sqlCom.Dispose()
        myDatabase.Close()
    End Sub

    Private Sub NumericUpDown1_ValueChanged(sender As Object, e As EventArgs) Handles NumericUpDown1.ValueChanged

        If NumericUpDown1.Value > 8 Then
            bugidTB.Clear()
            languageTB.Clear()
            variabledependantCB.CheckState = 0
            variationsCB.CheckState = 0
            generalstringTB.Clear()
            regexTB.Clear()
            descriptionTB.Clear()
            wantedcasesTB.Clear()
            extendedexplanationTB.Clear()
            solutionTB.Clear()
            fixTB.Clear()
            commitTB.Clear()
        End If

        myDatabase = New MySqlConnection("server=danu6.it.nuigalway.ie;user id=mydb2831ms;password=vi3mon;database=mydb2831")
        Dim strQuery As String = "SELECT * FROM BugHive WHERE Num=@Num"
        Dim sqlCom As MySqlCommand = New MySqlCommand(strQuery, myDatabase)
        With sqlCom
            .Parameters.AddWithValue("@Num", NumericUpDown1.Value)
        End With
        myDatabase.Open()

        Dim DR As MySqlDataReader = sqlCom.ExecuteReader()
        While DR.Read()
            bugidTB.Text = DR.GetString(0)
            languageTB.Text = DR.GetString(1)
            variabledependantCB.CheckState = DR.GetValue(2)
            variationsCB.CheckState = DR.GetValue(3)
            generalstringTB.Text = DR.GetString(4)
            regexTB.Text = DR.GetString(5)
            descriptionTB.Text = DR.GetString(6)
            wantedcasesTB.Text = DR.GetString(7)
            extendedexplanationTB.Text = DR.GetString(8)
            solutionTB.Text = DR.GetString(9)
            fixTB.Text = DR.GetString(10)
            commitTB.Text = DR.GetString(11)
        End While
        DR.Close()
        myDatabase.Close()
    End Sub

    Private Sub DELETE_Click(sender As Object, e As EventArgs) Handles DELETE.Click

        myDatabase = New MySqlConnection("server=danu6.it.nuigalway.ie;user id=mydb2831ms;password=vi3mon;database=mydb2831")
        Dim strQuery As String = "DELETE FROM BugHive WHERE Num=@Num"
        Dim sqlCom As MySqlCommand = New MySqlCommand(strQuery, myDatabase)
        With sqlCom
            .Parameters.AddWithValue("@Num", NumericUpDown1.Value)
        End With
        Dim result As DialogResult = MsgBox("Are you sure you want to delete this bug?", MsgBoxStyle.YesNo)


        If result = DialogResult.No Then
            Return
        ElseIf result = DialogResult.Yes Then
            myDatabase.Open()
            sqlCom.ExecuteNonQuery()
            myDatabase.Close()
        End If
    End Sub


End Class