# Environment setting for MacOS

## Step1 Install java
* Install: https://adoptopenjdk.net/releases.html?variant=openjdk15&jvmVariant=hotspot
![java version](img/img_macos/java_version.jpg)

## Step2 Set java environment
* Edit the following file.(`~/.bashrc`)
```
.bashrc
```

* Set the Environment ```export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_241.jdk/Contents/Home``` (Add to the file)
![java environment](img/img_macos/java_environment.png)

* **Open the terminal and enter ```source ~/.bashrc``` to apply the setting**
![source bashrc](img/img_macos/source_bashrc.png)

## Step3 Verify java installation
* Open Terminal and enter the command ```java -version```  
* The result will be the same as below
![java verify](img/img_macos/java_verify.jpg)

## Step4 Install Maven
* Install: https://maven.apache.org/download.cgi
![maven version](img/img_macos/maven_version.png)

## Step5 Extract the Maven Archive
* Put the folder `apache-maven-3.6.3` at the ```/usr/local```
![maven download](img/img_macos/maven_download.png)

## Step6 Set Maven Environment Variables

* Edit the `.bashrc` file.
* Add the lines to the file
```
export M2_HOME=/usr/local/apache-maven-3.6.3
export M2=$M2_HOME/bin
export MAVEN_OPTS="-Xms256m -Xmx512m"
```
* Add the following line to the `.bashrc` file
```
export PATH=$M2:$PATH
```  
![maven environment](img/img_macos/maven_environment.png)

* **Open the terminal and enter ```source ~/.bashrc``` to apply the setting**
![source bashrc](img/img_macos/source_bashrc.png)

## Step7 Verify maven installation
* Open the terminal and enter ```mvn --version```
* The result will be the same as below
![maven verify](img/img_macos/maven_verify.jpg)

## Step8 install Visual Studio Code
* Install: https://code.visualstudio.com/
