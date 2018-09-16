Imports System.Data.SqlClient
Imports System.IO
Imports System.Net
Imports System.Security.AccessControl
Imports MySql.Data.MySqlClient

Public Class Form1

    Dim RA As String
    Dim RAR As String
    Dim BF As String
    Dim CP As String
    Dim NB As String
    Dim numLines As Integer
    Dim status As String
    Dim fileReader As System.IO.StreamReader
    Dim BugHive As New Form3
    Dim myDatabase As New MySqlConnection
    Public numbugs As Integer
    Public bugs(12) As Integer

    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load

        Main()
    End Sub

    Sub Main()
        FindVals()
        fileReader.Close()

        Using Twitter As New WebClient
            Twitter.Credentials = New NetworkCredential("BugBot16", "7623sonyer")
            ServicePointManager.Expect100Continue = False
            ' Dim MyTweet = System.Text.Encoding.UTF8.GetBytes("status=" & Tweet.Text)

        End Using

    End Sub



    Sub FindVals()
        fileReader = My.Computer.FileSystem.OpenTextFileReader("C:/Users/user/Desktop/BugBotConfig.txt")
        Dim stringReader As String
        Dim endindex As Integer
        Dim startindex As Integer


        For i = 0 To 9
            stringReader = fileReader.ReadLine()
            If stringReader.Contains("ReposAccessed=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                StatVal1.Text = stringReader.Substring(startindex, l)
            End If
            If stringReader.Contains("RepoAccessRequests=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                StatVal2.Text = stringReader.Substring(startindex, l)
            End If
            If stringReader.Contains("bugsFound=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                StatVal3.Text = stringReader.Substring(startindex, l)
            End If
            If stringReader.Contains("changesPushed=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                StatVal4.Text = stringReader.Substring(startindex, l)
            End If
            If stringReader.Contains("bugsNum=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                numbugs = Integer.Parse(stringReader.Substring(startindex, l))
                StatVal5.Text = stringReader.Substring(startindex, l)
            End If
            If stringReader.Contains("QueueLength=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                qlengthLabel.Text = stringReader.Substring(startindex, l)
            End If
            If stringReader.Contains("CurrentRepo=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                RepoName.Text = stringReader.Substring(startindex, l)
            End If
            If stringReader.Contains("Status=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                status = stringReader.Substring(startindex, l)
            End If

        Next
        checkStatBox()
        checkBugDistribVals()
    End Sub


    Sub checkBugDistribVals()
        fileReader = My.Computer.FileSystem.OpenTextFileReader("C:/Users/user/Desktop/BugBotConfig.txt")
        Dim stringReader As String
        Dim endindex As Integer
        Dim startindex As Integer


        For i = 0 To 22
            stringReader = fileReader.ReadLine()
            If stringReader.Contains("j_cmr=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                bugs(0) = Integer.Parse(stringReader.Substring(startindex, l))
            End If
            If stringReader.Contains("j_ecs=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                bugs(1) = Integer.Parse(stringReader.Substring(startindex, l))
            End If
            If stringReader.Contains("j_aiv=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                bugs(2) = Integer.Parse(stringReader.Substring(startindex, l))
            End If
            If stringReader.Contains("j_irv=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                bugs(3) = Integer.Parse(stringReader.Substring(startindex, l))
            End If
            If stringReader.Contains("j_ioi=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                numbugs = Integer.Parse(stringReader.Substring(startindex, l))
                bugs(4) = Integer.Parse(stringReader.Substring(startindex, l))
            End If
            If stringReader.Contains("j_idN=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                bugs(5) = Integer.Parse(stringReader.Substring(startindex, l))
            End If
            If stringReader.Contains("j_abc=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                bugs(6) = Integer.Parse(stringReader.Substring(startindex, l))
            End If
            If stringReader.Contains("j_wabc=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                bugs(7) = Integer.Parse(stringReader.Substring(startindex, l))
            End If
            If stringReader.Contains("c_avc=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                bugs(8) = Integer.Parse(stringReader.Substring(startindex, l))
            End If
            If stringReader.Contains("c_did=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                bugs(9) = Integer.Parse(stringReader.Substring(startindex, l))
            End If
            If stringReader.Contains("c_irv=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                bugs(10) = Integer.Parse(stringReader.Substring(startindex, l))
            End If
            If stringReader.Contains("c_wavc=") Then
                startindex = stringReader.LastIndexOf("=") + 1
                endindex = stringReader.Length
                Dim l As Integer = endindex - startindex
                bugs(11) = Integer.Parse(stringReader.Substring(startindex, l))
            End If
        Next


    End Sub

    Sub checkStatBox()
        If status.Equals("Finding") Then
            StatusTrackBar.Value = 7
        End If
        If status.Equals("Cloning") Then
            StatusTrackBar.Value = 6
        End If
        If status.Equals("Scanning") Then
            StatusTrackBar.Value = 5
        End If
        If status.Equals("Identifying") Then
            StatusTrackBar.Value = 4
        End If
        If status.Equals("Debugging") Then
            StatusTrackBar.Value = 3
        End If
        If status.Equals("Pushing") Then
            StatusTrackBar.Value = 2
        End If
        If status.Equals("Finishing") Then
            StatusTrackBar.Value = 1
        End If
    End Sub

    Private Sub RefreshButton_Click(sender As Object, e As EventArgs) Handles RefreshButton.Click
        FindVals()
        fileReader.Close()
    End Sub

    Private Sub ResetButton_Click(sender As Object, e As EventArgs) Handles ResetButton.Click
        ResetConfigeFile()
        DeleteLocalRepos()
        ClearDBs()
    End Sub

    Sub ResetConfigeFile()
        Dim FileToDelete As String
        FileToDelete = "C:/Users/user/Desktop/BugBotConfig.txt"
        If System.IO.File.Exists(FileToDelete) = True Then
            My.Computer.FileSystem.DeleteFile(FileToDelete)
        End If
        ' Copy the file to a new folder and rename it.
        My.Computer.FileSystem.CopyFile(
            "C:/Users/user/Desktop/BugBotConfigDefaults.txt",
            "C:/Users/user/Desktop/BugBotConfig.txt")
    End Sub

    Sub DeleteLocalRepos()

        Dim DirToDelete As String
        DirToDelete = "C:/Users/user/Desktop/GitRepos/"

        ' Remove the access control entry from the file.
        'File.SetAccessControl(DirToDelete, New FileSecurity(DirToDelete, AccessControlSections.All.)
        My.Computer.FileSystem.DeleteDirectory(DirToDelete, FileIO.UIOption.AllDialogs, FileIO.RecycleOption.SendToRecycleBin)

        My.Computer.FileSystem.CreateDirectory(DirToDelete)
    End Sub

    Sub ClearDBs()
        myDatabase = New MySqlConnection("server=danu6.it.nuigalway.ie;user id=mydb2831ms;password=vi3mon;database=mydb2831")

        Dim strQuery As String = "DELETE From Queue"
        Dim sqlCom As MySqlCommand = New MySqlCommand(strQuery, myDatabase)
        myDatabase.Open()
        sqlCom.ExecuteNonQuery()
        sqlCom.Dispose()
        MsgBox("queue cleared")
        myDatabase.Close()
        Dim strQuery2 As String = "DELETE From Record"
        Dim sqlCom2 As MySqlCommand = New MySqlCommand(strQuery2, myDatabase)
        myDatabase.Open()
        sqlCom2.ExecuteNonQuery()
        sqlCom2.Dispose()
        myDatabase.Close()
        MsgBox("record cleared")
    End Sub

    Private Sub BugHiveButton_Click(sender As Object, e As EventArgs) Handles BugHiveButton.Click

        Dim BugHive As New Form3
        BugHive.Show()

    End Sub


    ' Removes an ACL entry on the specified file for the specified account.
    Sub RemoveFileSecurity(ByVal fileName As String, ByVal account As String,
        ByVal rights As FileSystemRights, ByVal controlType As AccessControlType)

        ' Get a FileSecurity object that represents the 
        ' current security settings.
        Dim fSecurity As FileSecurity = File.GetAccessControl(fileName)

        ' Remove the FileSystemAccessRule from the security settings. 
        fSecurity.RemoveAccessRule(New FileSystemAccessRule(account,
            rights, controlType))

        ' Set the new access settings.
        File.SetAccessControl(fileName, fSecurity)

    End Sub

    Private Sub Graphs_Click(sender As Object, e As EventArgs) Handles Graphs.Click

        Dim graphs As New Form5
        graphs.Show()
    End Sub
End Class
