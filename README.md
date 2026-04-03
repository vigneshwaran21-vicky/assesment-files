Common commends:
A) Setup & Configuration 
• git --version 
→ Shows the installed Git version. 
• git config --global user.name "Your Name" 
→ Sets your username globally. 
• git config --global user.email you@example.com 
→ Sets your email globally. 
B) Create or Clone Repositories 
• git init 
→ Creates a new Git repository. 
• git clone <repository-url> 
→ Clones a remote repository. 
C) Check Status 
• git status 
→ Shows file changes and staging info. 
D) Add Files (Staging Area) 
• git add file.txt 
→ Stages a specific file. 
• git add . 
→ Stages all changed files. 
E) Commit Changes 
• git commit -m "Message" 
→ Creates a new commit. 
• git commit --amend 
→ Modifies the previous commit. 
F) View History 
• git log 
→ Shows full commit history. 
• git log --oneline 
→ Condensed commit history. 
• git log --oneline --graph --all 
→ Visual branch history. 
G) Branching 
• git branch 
→ Lists branches. 
• git branch <name> 
→ Creates a new branch. 
• git checkout <name> 
→ Switches branch. 
• git checkout -b <name> 
→ Creates & switches to a branch. 
H) Merging 
• git merge <branch> 
→ Merges a branch into current. 
I) Remote Repository Commands 
• git remote add origin <url> 
→ Adds remote repo. 
• git remote -v 
→ Shows remotes. 
• git push origin main 
→ Pushes changes. 
• git push -u origin main 
→ Pushes & sets upstream. 
• git pull origin main 
→ Fetches & merges. 
• git fetch 
→ Fetches without merging. 
J) Undo / Reset / Restore 
• git restore file.txt 
→ Restores file to last commit. 
• git restore --staged file.txt 
→ Unstages a file. 
• git reset --soft HEAD~1 
→ Undo commit, keep staged. 
• git reset --mixed HEAD~1 
→ Undo commit, keep changes. 
• git reset --hard HEAD~1 
→ Undo commit and delete changes. 
• git revert <commit-id> 
→ Creates a reverse commit. 
K) Stash (Temporary Save) 
• git stash 
→ Saves work temporarily. 
• git stash list 
→ Shows all stashes. 
• git stash apply 
→ Applies stash. 
• git stash pop 
→ Applies & removes stash. 
L) Tagging 
• git tag v1.0 
→ Creates a tag. 
• git push origin v1.0 
→ Pushes tag to remote.
DA 3 
Mavens project git:
Delete Readme after creating all files
Pom.xml:
CODE:
<project xmlns="http://maven.apache.org/POM/4.0.0"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
 http://maven.apache.org/xsd/maven-4.0.0.xsd">
 <modelVersion>4.0.0</modelVersion>
 <groupId>com.example</groupId>
 <artifactId>simple-maven-app</artifactId>
 <version>1.0-SNAPSHOT</version>
 
 <dependencies>
   <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.13.2</version>
       <scope>test</scope>
   </dependency>
 </dependencies>
</project>
Src/main/java/com/example/App.java
package com.example;
public class App {
 public int add(int a, int b) {
 return a + b;
 }
}
COMMIT THE CHANGES
Comw out and again add file option 
Create file
Src/test/java/com/example/AppTest.java
package com.example;
import org.junit.Test;
import static org.junit.Assert.*;
public class AppTest {
 @Test
 public void testAdd() {
 App app = new App();
 assertEquals(5, app.add(2, 3));
 }
}
copy the git repository link clone with vs code 
open new terminal type “mvn clean test”
open Jenkins: click new item type project title select pipeline and save 
open that new project and go inside 
in configure- trigger give pipeline script
pipeline {
 agent any
 tools {
 maven 'M3'
 }
 stages {
 stage('Checkout Git') {
 steps {
 git branch: 'main',
 url: 'https://github.com/balaganeshn/simple-maven-app'
 }
 }
 stage('Build and Test') {
 steps {
 bat 'mvn clean test'
 }
 }
 }
}
UPDATE THE GITHUB url TO YOUR REPOSITORY URL: 
CLICK APPLY AND SAVE, IN THE NEXT SCREEN CLICK ON BUILD NOW
Console output: bulid success

DA 4:
Dockers:
Github 
deployment.yaml

apiVersion: apps/v1
kind: Deployment
metadata:
  name: html-demo
spec:
  replicas: 2
  selector:
    matchLabels:
      app: html-demo
  template:
    metadata:
      labels:
        app: html-demo
    spec:
      containers:
      - name: html-demo
        image: swetab84/html-demo:latest
        ports:
        - containerPort: 80

---
apiVersion: v1
kind: Service
metadata:
  name: html-demo-service
spec:
  type: NodePort
  selector:
    app: html-demo
  ports:
  - port: 80
    targetPort: 80
    nodePort: 30010

Create index.html:

<!DOCTYPE html>
<html>
<head>
<title>CI/CD Demo</title>
<style>
body{
text-align:center;
font-family:Times New Roman;
background-color:#af0d0d;
}
h1{
color:#0ef777;
}
</style>
</head>
<body>
<h1>CI/CD Pipeline Working</h1>
<h2>Deployed using Jenkins + Docker + Kubernetes</h2>
<p>This webpage is automatically deployed using a CI/CD pipeline.</p>
</body>
</html>
Create a Dockerfile
FROM nginx:latest
COPY index.html /usr/share/nginx/html/index.html
EXPOSE 80
GO to dockerhub -> In settings check kubernets check its enable and also check kubeadm
After that open Jenkins-> In Jenkins Setting --> Credentials -> system global click -> add crentencials->  (Docker hub)User name:  Password:    ID:dockerhub and then click create
add Credentials – kind:Secret file –>file(choose config)->id kuberconfig->description: Kubernetes crendentials-> and click create
After this : Create New Item -> Pipeline Paste the code in pipeline script - Update URL of GIT repository - Make sure credentialsId: “dockerhub” username
Pipeline: 

pipeline {
agent any
environment {
DOCKER_IMAGE = "swetab84/html-demo"
}
stages {
    stage('Clone Code') {
steps {
git branch: 'main',
url: 'https://github.com/swetab-max/docker-example.git'
}
}
stage('Build Docker Image') {
steps {
bat 'docker build -t %DOCKER_IMAGE% .'
}
}
stage('Push Image') {
steps {
withCredentials([usernamePassword(credentialsId: 'dockerhub',
usernameVariable: 'USER', passwordVariable: 'PASS')]) {
bat 'docker login -u %USER% -p %PASS%'
bat 'docker push %DOCKER_IMAGE%'
}
}
}
stage('Deploy to Kubernetes') {
steps {
withCredentials([file(credentialsId: 'kuberconfig', variable:
'KUBECONFIG')]) {
bat '''
set KUBECONFIG=%KUBECONFIG%
kubectl apply -f deployment.yaml --validate=false
'''
}
}
}
}
}
and then click build now and check console output
after this open docker: builders,containers,images->take all screenshot
open that link which mam provided So check if the HTML file open in http://localhost:30010 
DA 2 :
First go to Jenkins-> new item give project name ,select freestyle give create 
New project open and go to configure
echo hello Jenkins
save and click bulid now and go to console output
go to git create repository add two files(app.txt,Jenkins)
create a notedpad txt inside the Jenkins folder ->App.txt
jenkin app.txt is created hello
Go to Jenkins folder -> click and go inside and right click open terminal give all the commends and then it will be pushed to git then create a Jenkins file inside the repository
git init
git add .
git commit -m “Intial commit”
git brach -M main
git remote add origin <repository link>
git push -u origin main
Jenkins file
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Build'
            }
        }
        stage('Test') {
            steps {
                echo 'Test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy'
            }
        }
    }
}
Manage Jenkins->go to tools-> git installations give path from 
Create Freestyle Job-> Source Code Management → Git-> give url link from github

git add 
git commit -m “Code change”
git add jenkinsfile
git commit -m “Add jenkinsfile”
git push origin main
git add Jenkinsfile
git commit -m “updated pipeline stages”
git push

















