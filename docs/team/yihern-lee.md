---
layout: page
title: Yi Hern's Project Portfolio Page
---

### Project: HireLah

HireLah is a desktop app that helps recruiters to manage talent and job candidates by tracking every step of the hiring process, from offering positions to scheduling interviews with candidates. It is optimised for Command Line Interface (CLI) users while still offering a GUI, so that power users can accomplish tasks much quicker by using commands . It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

### Summary of Contributions:
Given below are my contributions to the project.

* **New Feature**: Position
    * New Position data type to represent a position in the company that is open for hire.
    * Position tracks the number of openings, as well as, the number of offers handed out the applicants.
    * Position contains other fields, such as, description and requirement tags, to allow recruiters to associate information with the position.
    * Other notable contributions to support this feature:
      * Parsers to support `add`, `delete` and `edit` commands for Positions.
      * Command logic for executing `add`, `delete` and `edit`.
      * Methods in `Model` to support the cascading effects for editing a Position.
      * Unit tests to cover the input validation for `PositionName`, `PositionOpenings`, `Description` and `Requirement`.
      * Unit tests to cover the execution correctness for `AddPositionCommand`, `DeletePositionCommand`, `EditPositionCommand` and their various parsers.
* **Code contributed**: Contributed over [3,000+ L0C](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=yihern-lee&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
to the project.

* **Project management**:
    * Reviewed 23 PRs.
    * Left constructive feedbacks on PRs [#76](https://github.com/AY2122S2-CS2103-W17-4/tp/pull/76), [#83](https://github.com/AY2122S2-CS2103-W17-4/tp/pull/83),
  [#109](https://github.com/AY2122S2-CS2103-W17-4/tp/pull/109), [#156](https://github.com/AY2122S2-CS2103-W17-4/tp/pull/156),
      [#158](https://github.com/AY2122S2-CS2103-W17-4/tp/pull/158).
    * Set up issue for main v1.3 feature of tracking interview status [#122](https://github.com/AY2122S2-CS2103-W17-4/tp/issues/122)

* **Enhancements to existing features**:
    * Made changed or advised on changes to the validation regex for fields in Applicant (refactored from Person).
      * Regex to ensure that at least one alphabet exists in `Name` and length is shorter than 100 characters.
    * Enhancements to `ModelManager` to support cascading effects of editing `Position` and `Applicant`.

* **Documentation**:
    * User Guide:
        * Add documentation for the features `add -p`, `edit -p`, and `delete -p`.
        * Contributions to explain the validation logic in regard to `Position`, under `pass` and `accept` commands.
    * Developer Guide:
        * Use case for editing position.
        * Explanation for the implementation of Position.
        * Sequence diagram for `DeleteApplicantCommand`.
        * Update diagram to reflect new class name `HireLah` instead of `AddressBook`.
        * Add manual testing instructions for `add` command.
    
* **Community**:
    * Contribute 15 bug reports for PE dry run.
    * Forum contributions:
      * Question to enhance the closing process for iP chatbot project [#127](https://github.com/nus-cs2103-AY2122S2/forum/issues/127).
      * Contributed to discussion on the immutable design for data classes in tP [#193](https://github.com/nus-cs2103-AY2122S2/forum/issues/193).
