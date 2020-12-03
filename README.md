# SimpleDataFrame 

## This is a simple data frame project that my team and I worked together. <br><br> 

Hello guys! (And girls too xd) So you must be wondering what is git right?  
Let me give you a step-by-step guide to get familiar with git and github.  <br><br>

### FIRST PART  <br>
************* 
<br>1. Download **[Git](https://git-scm.com/downloads)** inside your computer (dun worry there is mac and windows version, you will be covered)  
> - The step are quite confusing and quite complicated, 
> - I believe that most of you dun understand what does the setting means.
>     - **[Click here to see a video on how to download it](https://www.youtube.com/watch?v=wSGuir8BUeU&t=53s)** 

<br><br>2. You will be wondering what is git and github right?    
To have a clearer explanation, **[click here to understand what is this shit all about xd](https://www.youtube.com/watch?v=DVRQoVRzMIY&t=1145s)**
> Inside the video, he uses "sublime text" as his text editor, it is basically the same thing as "Vs code" or even "notepad"  
> For me, I like Vs code since it is looks more modern and stylish xd

<br><br>3. Basic commands in git  
**YOU MUST BE INSIDE THE FOLDER THAT CONTAINS THE .git FILE OR ELSE YOU CAN'T USE GIT COMMANDS**   
<br>Open up the git bash or command prompt <br>
**a. Let us start from configuring your global username and email**     
| Commands                                                    	| Explanation                                   	|
|-------------------------------------------------------------	|-----------------------------------------------	|
| git config --global user.name "YOUR_GITHUB_USERNAME"        	| This set the name into git inside your system 	|
| git config --global user.email "YOUR_EMAIL_THAT_HAS_GITHUB" 	| This set the email into git                   	| <br><br>

You will be guided to login to your account, just login

b. Let's learn some basic of git commands    
Later I will let you all to do it practically dont worry    
| Commands                                  	| Explanation                                                                         	|
|-------------------------------------------	|-------------------------------------------------------------------------------------	|
| git init                                  	| create a new local git repo (you can create a new folder and use this command)      	|
| git add .                                 	| Add all files into the staging area                                                 	|
| git status                                	| Display the list of changed files and the files that are yet to be staged or commit 	|
| git commit -m "YOUR_MESSAGE"              	| Create a snapshot of changes and save it into git                                   	|
| git pull                                  	| merges all the changes from the remote repo to the local git repo                   	|
| git push -u origin < master>               	| send local changes (after commit) to the local repo to update it                    	|
| git remote add origin <host_or_remoteURL> 	| connect the local repo to the remote repo                                           	|
| git clone URLfromGithub                   	| cloning all the file inside the repo and download it into your local system         	| <br><br>


### SECOND PART (DON'T DO THIS UNLESS YOU HAD FINISH FIRST PART) 

************

Let's have some fun with the command line you learnt   
1. Open your command prompt or bash   <br>  
2. Then use this command to download the repo into your system   
> `git clone https://github.com/Lhz0616/SimpleDataFrame.git` <br>

##### You're done!! The repo is inside your system locally!  
Make sure that you know where is the file located.    <br><br><br>

3. Now let's try to add your name into the README.md file   <br>

| No 	| Steps                                            	|
|----	|--------------------------------------------------	|
| 1  	| Open the README.md file in your text editor      	|
| 2  	| Add your name down below and save it             	|
| 3  	| Add the file to the staging area                 	|
|    	| `git add README.md`                              	|
| 4  	| Check your fie status                            	|
|    	| `git status`                                     	|
| 5  	| Commit your changes in your local machine        	|
|    	| `git commit -m "Your_name added name"`           	|
| 6  	| Push your changes to the remote repo main branch 	|
|    	| `git push -u origin main`                         | <br>

**Please note that origin is the name of the remote repo and main is the default name of the branch**<br><br>


1. Lim Hong Zhi \\ hello guys and girls xd <br>
2. <br>
3. <br>  
4. <br>
5. <br>   


### THIRD PART(DONT'T come here unnless you had finish the previous part)    

*************

I believe that you might be wondering how to make sure that your repo in your local system is always the most updated one with the GitHub.   
Since your local repo is not directly linked to the remote repo.   
We need to do it manually.   
If you wanted to update the local repo, you should try this. 

`git pull origin main`    <br><br>
After that you will have the most updated version of your repo in the local system. 

