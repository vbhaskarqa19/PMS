Feature: GoogleDrive API Test 

Background: 
	Given url 'https://www.googleapis.com' 
	#provide valid access token to googleapis
	Given def access_token = 'ya29.a0AfH6SMBcklM_D4gz9UNOJ-t3Q6HHi30DNqcJutIetX369n5EgKbNj-L74fxV2Eo1ymHXMLy3mWD_n-syPCJHc9Dl-XmNGg4VxGVI7c2NfVjwtMC7NTsJqgQ0gjsBGV8nytyqzV252fTusE9hvk-nNnSJlcF6' 
	
Scenario: CRUD on Google Drive folder and files 

# Create Folder
	Given path 'drive/v3/files' 
	And header Authorization = 'Bearer ' + access_token 
	When request {name : 'API', mimeType : 'application/vnd.google-apps.folder'} 
	When method POST 
	Then status 200 
	Then print 'response--' response 
	
	Given def folderId = response.id 
	
# Create file In above folder
	Given path 'drive/v3/files' 
	And header Authorization = 'Bearer ' + access_token 
	When request {name : 'API_file.doc', parents : [#(folderId)]} 
	When method POST 
	Then status 200 
	Then print 'response--' response 
	
	Given def fileId = response.id 
	
# Download file	
	Given path 'drive/v3/files/'+ fileId 
	And param alt = 'media' 
	And param acknowledgeAbuse = 'false' 
	And header Authorization = 'Bearer ' + access_token 
	And header accept = 'application/msword' 
	When method GET 
	Then status 200 
	Then print 'response--' response 
	
# Delete file	
	Given path 'drive/v3/files/'+ fileId 
	And header Authorization = 'Bearer ' + access_token 
	When method DELETE 
	Then status 204 
	Then print 'response--' response 
	
# Delete folder	
	Given path 'drive/v3/files/'+ folderId 
	And header Authorization = 'Bearer ' + access_token 
	When method DELETE 
	Then status 204 
	Then print 'response--' response