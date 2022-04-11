---
layout: page
title: Khoa's Project Portfolio Page
---

### Project: HireLah

HireLah is a CLI-based app that offers help for recruiters who need to managed hundreds of applicants per day.
By having short and simple syntax, HireLah helps to accelerate the normal scheduling and filtering process.
The app is most suitable for CLI-preferred users, but GUI is also provided to choose.  It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Sort feature in `list` command
  * Added new optional parameters to the list command to allow sorting of different data types.
  * This allows user to easily view the data with respect of the first and last occurrence.
  * Justification: The user may pose a need to organize the data, or view the data from bottom up or top down. 
`sort` feature helps user can perform the action quickly when having a large dataset.
  * Highlights: The `sort` command sort the internal storing list, instead of the viewing list. 
Hence, the sorting effect can retain after user close and reopen HireLah.

* **New Feature**: Export CSV command
  * New command allows user to export the HireLah data into a CSV file.
  * This gives user ability to perform other operations on `applicants`, `interview` and `position` data in their
respective CSV files.
  * Justification: Although HireLah target user is recruiters, they will also need to submit report to their respective
supervisors. Therefore, an appropriate command is needed to capture the need of the target user. As company typically
uses Excel as a mean of reporting, `export` command output the data into CSV file, which can then easily open and 
manipulate by Excel.
  * Highlights: The `export` command only output the data currently displayed in HireLah. Thus, user
can perform operation like `filter` or `sort` to get the desired data before `export`.
  
* **Code contributed**: 
  * Contributed [Over 1500+ LoC](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=khoahre123&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
  to the project

* **Project management**:
  * Managed `Issues` and `Milestone` progress before each iteration.
  * Updated meeting minutes in weekly team meetings.
  * Started a mock testing session to increase quality of the product.

* **Enhancements to existing features**:
  * Extended the storage to stores `interview` and `position`
  * Updated description for `help` command, include adding new description for new commands
  * Modified `HelpWindow` to displayed specific description for each commands.

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `help`
    * Added documentation for the features sort in `list` command
    * Added documentation for the feature `export`
  * Developer Guide:
    * Updated class diagram `Storage` component and their descriptions
    * Added use cases for viewing help, sorting and exporting data
    * Updated implementation details of `sort` feature and added UML Sequence diagram as an example.

* **Community**:
  * Reviewed PRs within the team (Over [30 PRs](https://github.com/AY2122S2-CS2103-W17-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Akhoahre123))
  * Contributed [10 issues](https://github.com/khoahre123/ped/issues) during PE dry run
  
