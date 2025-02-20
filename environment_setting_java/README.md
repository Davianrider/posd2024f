# Environment Setting
## Important Links
* [GitLab](https://ssl-gitlab.csie.ntut.edu.tw): https://ssl-gitlab.csie.ntut.edu.tw

* [Jenkins](https://ssl-jenkins.csie.ntut.edu.tw): https://ssl-jenkins.csie.ntut.edu.tw

* [Facebook](https://www.facebook.com/groups/539803243647591): https://www.facebook.com/groups/539803243647591

  ![facebook QR code](./img/fb_qrcode.png)

---

## Category
1. Computer Environment Setting For [Windows](Environment_Windows.md) / [MacOS](Environment_MacOS.md)
1. [GitLab Setting](#gitlab-setting)
1. [Maven Project Setting](#maven-project-setting)
1. [Homework Upload Tutorial](#homework-upload-tutorial)

---

## Computer Environment Setting

- [Windows](Environment_Windows.md)
- [MacOS](Environment_MacOS.md)

---

## Gitlab Setting
1. Go to your t${STUDENT_ID}@ntut.org.tw to check your gitlab mail, and set your password.

1. Go to [NTUT ssl gitlab](https://ssl-gitlab.csie.ntut.edu.tw), and try to login the account.
![gitlab_login](./img/gitlab/gitlab_login.png)

1. Select "Create a project" to create a new project.

**Important**:
1. The project name must be **${COURSE_NAME}_${STUDENT_ID}_HW**, where

    COURSE_NAME = dp2021s

    STUDENT_ID  = Your student id. e.g. 107820000

1. Set project visibility level as **private**.
![Select Create Project](./img/gitlab/selectCreate.png)
![Create Project](./img/gitlab/createProject.png)

### 3. Set webhook

1. Go to **Setting -> Integrations**<br/>
![gitlab integration](./img/gitlab/gitlab_integration.png)

1. Fill in url: 
  
    https://root:11edd45bb986ee85b872d28a2d15e2aecb@ssl-jenkins.csie.ntut.edu.tw/project/${COURSE_NAME}_${STUDENT_ID}_HW

    COURSE_NAME = dp2021s
    
    STUDENT_ID  = Your student id. e.g. 107820000


1. Keep Secret Token as empty.

1. Select **Push events** in Trigger selection

1. Unselect **Enable SSL verification** in SSL verification.

1. Click "Add webhook"<br/><br/>
![webhook](./img/gitlab/webhookSetting.png)

---

## Maven Project Setting

### Install Java Extension Package in VSCode
1. Search `Java` in `Extensions`
1. Install `Java Extension Pack`
![package](./img/vscode/install_java_extension_package.jpg)

### Create a maven project
1. Click F1
1. Type `maven`
1. Select `Maven: create Maven Project`

    ![](./img/vscode/create_maven_project.jpg)

1. Select `maven-archetype-quickstart`


    ![](./img/vscode/maven_quickstart.jpg)

1. Select `1.4`
1. Select your project destination folder
1. Fill in `groupId`, `artifactId`, `package` as below
![](./img/vscode/maven_project_info.jpg)

---

## Homework Upload Tutorial

### First Setting(Only first time needs to do setting)
1. Open the terminal and enter the following command.<br/><br/>
Get in to project directory and initialize as git project.
    ```
    $ cd /path/to/your/project
    $ git init
    ```
    ![git init](./img/homework/gitInit.png)

1. Go to [GitLab](https://ssl-gitlab.csie.ntut.edu.tw) to find your own project link.<br/>
**Suggestion**: Use https link instead of ssh link.<br/><br/>
![repoLink](./img/homework/repoLink.jpg)

1. Link your folder to Gitlab repository.

    Enter following command:

    ```
    $ git remote add origin ${YOUR_REPO_URL}
    ```

    Replace ${YOUR_REPO_URL} with the gitlab project link.
    ![Add origin](./img/homework/AddOrigin.png)

### Upload Homework
1. Look up the current status of git repository.

    Enter following command:

    ```
    $ git status
    ```

    ![Git status](./img/homework/GitStatus.png)

1. Add the new/modified files to "Changes to be committed" queue:

    Enter following command:

    ```
    $ git add .
    ```

    ![Git Add](./img/homework/gitAdd.png)
    
    If the file status is "deleted", you'll need rm command:

    ```
    $ git rm ${FILE_NAME}
    ```

    ![Git rm](./img/homework/gitRm.png)

1. Check the current status again.

    ```
    $ git status
    ```

    ![Git status 2](./img/homework/GitStatus2.png)

1. If the status in step 3 is correct, you can commit the current changes to git history.

    By following command:

    ```
    $ git commit -m "${YOUR_COMMIT_MESSAGE}"
    ```

    ${YOUR_COMMIT_MESSAGE} is the message to describe the current changes you commit.
    ![Git Commit](./img/homework/gitCommit.png)

1. If it's your first time to commit in your device, system will require you to provide your personal info.

    Enter the following commands to fill in infos.

    ```
    $ git config --global user.email ${YOUR_EMAIL}
    $ git config --global user.name ${YOUR_NAME}
    ```

    Then you can enter the commit message in step 4 again.

1. Push your local commits to Gitlab server.
    Enter the following command:

    ```
    $ git push -u origin master
    ```

    And enter your username & password.

    **The password won't show when you enter.**
    ![Git push](./img/homework/gitPush.png)

### Check homework status
1. Go to [Gitlab](https://ssl-gitlab.csie.ntut.edu.tw) to check your upload status.

    ![Gitlab status](./img/homework/GitlabStatus.jpg)

1. Go to [Jenkins](https://ssl-jenkins.csie.ntut.edu.tw) to check your homework status.

    The username and password are default in your **student ID**<br/>
    For example, username: 106820000, password: 106820000<br/>
    Please login and modified to your own password

* Projects to check:
  * Check ${COURSE}_${STUDENT_ID}_HW, for example: oop2019s_106820000_HW.<br/>- This project is for executing student's own unit tests.
  * Check ${COURSE}_${STUDENT_ID}_HW_TA, for example: oop2019s_106820000_HW_TA.<br/>- This project is for executing TA provided unit tests.
* Project status:
  * Blue: all unit tests passed.
  * Red: some tests failed, or compile failed.<br/><br/>
![Jenkins Status](./img/homework/JenkinsStatus.png)

    If you click blue/red light, you can see the execution console to check execution status.<br/><br/>
    ![Jenkins Console](./img/homework/JenkinsConsole.png)
