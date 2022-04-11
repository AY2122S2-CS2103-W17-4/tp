---
layout: page
title: Wei Howe's Project Portfolio Page
---

### Project: HireLah

### Overview
HireLah is a desktop app that helps recruiters to manage talent and job candidates by tracking every step of the hiring 
process, from offering positions to scheduling interviews with candidates. It is optimised for Command Line Interface 
(CLI) users while still offering a GUI, so that power users can accomplish tasks much quicker by using commands . It is 
written in Java, and has about 10 kLoC.

### Summary of Contributions:
Given below are my contributions to the project.

* **New Feature**: `Interviews`
  * New Interview data type to represent an `Applicant` 
  attending an `Interview` for a `Position`.
  * A successful interview allows the `Applicant` to be 
  matched with an open `Position`, if the applicant agrees to 
  `accept` the interview. Core functionality of HireLah which 
  which is do hiring and matching.
  * `add` / `delete` / `pass` / `fail` / `accept` / `reject` 
  commands for interview.


* Other notable contributions to support this feature:
  * Multiple Parsers to support various commands for Interviews.
  * Methods in `Model` to support the cascading effects for `passing`/`accepting` an Interview.
  * Some unit testing to ensure that commands work as intended.
  

* **Code contributed**: Over 1900 LoC [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=goalfix&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)


* **Project management**:
  * Main in charge of setting up issues in the Github issue tracker,
  and creating milestones for the team.
  * Reviewed 52 PRs.
  * Left constructive feedback on several PRs, including but not limited to [#76](https://github.com/AY2122S2-CS2103-W17-4/tp/pull/76), 
  [#154](https://github.com/AY2122S2-CS2103-W17-4/tp/pull/154) [#158](https://github.com/AY2122S2-CS2103-W17-4/tp/pull/158).
  * Helped out team with [release management](https://github.com/AY2122S2-CS2103-W17-4/tp/releases) for v1.3.1.
  * Provided git CLI advise and help to teammates.
  

* **Documentation**:
  * [User Guide](https://ay2122s2-cs2103-w17-4.github.io/tp/UserGuide.html):
    * Added documentation for the features of `interview`, including
    `add -i`, `delete -i`, `pass`, `fail`, `accept`, `reject`.
  * [Developer Guide](https://ay2122s2-cs2103-w17-4.github.io/tp/DeveloperGuide.html):
    * Added use case for adding applicants.
    * Updated sequence diagram for architecture overview to reflect renamed classes.
    * Added activity diagram and explanation for tracking interview status.
    * Added sequence diagram and design consideration for adding of data.
    
* **Community**:
  * Contributed [16 issues](https://github.com/goalfix/ped/issues) during PE dry run.
  * Contributed 9 posts to the module forum, including [reporting a potential bug](https://github.com/nus-cs2103-AY2122S2/forum/issues/228).
