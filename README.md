# ExtentReport4-testNG-java
This extent report has below Features:
- it can be easily plugged in to any Java - testNG framework for a beautiful Dashboard report.
- It has separate Listener configuration : TestListeners.java, which has default setting for all Test() based on pass, skip, fail and it will capture package name, method name into report, no need to explictly write it in Test() method. 
Only Test related info can be added.
- For Failed cases, stack trace error log and screenshot is added automatically by TestListeners.java, no need to add any extra steps.

Steps to add this report in existing project:
- Add ExtentReportManager.java and TestListerns.java in your config package or anywhere in your project
- Add lister  @Listeners({TestListeners.class})  in Test base as shown in Testbase.java. 
- Modify name and import Testbase class in TestListeners.java :  WebDriver driver = ((Testbase_thisshould be your Testbase class name) result.getInstance()).driver;
