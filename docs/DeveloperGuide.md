---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S2-CS2103-W17-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ApplicantListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S2-CS2103-W17-4/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S2-CS2103-W17-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

Note that `ApplicantListPanel`, `PositionListPanel`, and `InterviewListPanel` will all exists simultaneously in the `UI` component, but only one will be visible to the user as controlled by tabs in `MainWindow`.

The `UI` component,

* executes user commands using the `Logic` component.
* changes the selected tab automatically according to the `DataType` in `CommandResult` from the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays the `Applicant`, `Position` and `Interview` objects residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a applicant).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S2-CS2103-W17-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />
<br/>

In the `Model`, `ModelManager` contains three different `DataType` – `Applicant`, `Position` and `Interview`, each with their own `UniqueXYZList` contained in `AddressBook`. The class diagrams for each `DataType` are separated below for better clarity.

`Applicant` class diagram:

<img src="images/ModelApplicantClassDiagram.png" /> 

`Position` class diagram:

<img src="images/ModelPositionClassDiagram.png" /> 

`Interview` class diagram:

<img src="images/ModelInterviewClassDiagram.png" /> 

Note that the `Interview` class contains `Applicant` and `Position`.


The `Model` component,

* stores all the data i.e., all `Applicant`, `Position`, and `Interview` objects (which are contained in `UniqueApplicantList`, `UniquePositionList`, and `UniqueInterviewList` objects respectively).
* stores the currently 'selected' `Applicant`, `Position`, and `Interview` objects (e.g., results after a list command with filter applied) as a separate _filtered_ list which is exposed to outsiders as unmodifiable Java's Observable List (i.e., `ObservableList<Applicant>`, `ObservableList<Position>` and `ObservableList<Interview>` for the different types) that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Applicant feature

#### Proposed Implementation

An applicant in HireLah is represented by `Applicant`. `Applicant` is implemented by refactoring `Persons`.
Additionally, `Applicant` implements two new attributes which are represented by the following two new classes:

* `Gender` — M refers to male, and F refers to female. Only the value M or F is allowed.
* `Age` —  Numerical representation of the age of the applicant. Only values with two digits or more are allowed.

`Gender` and `Age` class highly resemble other existing attribute classes such as `Address`, `Email`, `Name`, and
`Phone`.

These classes are contained in the `applicant` package which belongs to the `model` package.

Applicant is implemented this way as for HireLah, we require new attributes such as `Gender` and `Age` to aid in the
recruitment process. the `Person` class did not contain such attributes.

Adding Gender and Age as tags using the existing functionality is not ideal as we do not want these attributes to be
optional.

A new `Applicant` class had to be created to support the functionality. It is also not ideal to keep the existing
`Person` class as it should not be instantiated by users in HireLah.

Hence it made sense to refactor `Person` to `Applicant` and to extend and build on the existing functionalities to
support the needs of HireLah.

### To Add: Position feature?

### To Add: Interview feature?

### Adding of Data 

#### Implementation

Adding of different data types is currently done through `ModelManger`, which implements the methods in interface `Model`.
There are 3 levels to the parsing of the add command from user input.
1. `AddressBookParser` identifies it as an `add` command.
2. `AddCommandParser` identifies the exact data type that is to be added, through the `flag` of the user input.
3. `AddXYZCommandParser` identifies the fields to be added for the specific datatype, and creates and `AddXYZCommand`.

#### Design considerations:

#### Aspect: How to add different data types:

* **Alternative 1 (current choice):** Have a general add command.
    * Pros: User-friendly since users only have to remember a singular command.
    * Cons: Requires additional levels of parsers to be created.

* **Alternative 2:** An individual command for each data type that can be added
    * Pros: Fewer levels of parsers is required.
    * Cons: We must ensure that the implementation of each individual command are correct.

### Deleting of Data

#### Implementation
The implementation of deleting data is similar to adding data, where deleting of different data types is done through `ModelManger`, which implements the methods in the `Model` interface.

The parsing of a delete command from user input is also done through the 3 levels system, with `AddressBookParser`, `DeleteCommandParser`, and `DeleteXYZCommandParser` which eventually creates the `DeleteXYZCommand`.

However, when deleting an applicant or a position, an additional step of cascading to delete interview is required. Since every interview is associated with an applicant and a position, we cannot have an interview exist without the corresponding applicant or position.
Hence, it is important to delete the associated interview(s) when deleting an applicant or a position.

#### Design considerations:

#### Aspect: How to cascade when deleting applicant/position to delete interview:

* **Alternative 1 (current choice):** Loop through all interviews in `DeleteXYZCommand`
    * Pros: Less coupling as a data type does not store another data type as an attribute.
    * Cons: May be less efficient as we have to loop through the whole list of interviews everytime when deleting applicant/position.


* **Alternative 2:** Keep relevant list of interviews for each applicant and position.
    * Pros: More efficient when deleting since all the associated interviews are already available.
    * Cons: Increased coupling between applicant, position, and interview which make it more bug-prone.
    
### Filtering of Data

#### Implementation

The implementation of filtering data is done as an extension of the `list -X` command, which takes in optional parameters that will trigger the filtering of data to display if given. The filtering of data is done similar to the `find` command in AB3, which is now deprecated in HireLah. It applies a predicate to the `filteredXYZ` filtered lists in the `ModelManager`, which the `UI` will pick up and display the latest filtered list of the data to the user.

To support different filters for different data types, each filter is a predicate class in the `Model` component. For example, to support filtering applicants by gender, there is a [`ApplicantGenderPredicate`](https://github.com/AY2122S2-CS2103-W17-4/tp/blob/master/src/main/java/seedu/address/model/applicant/ApplicantGenderPredicate.java) in the `Model` component under `applicant`. The predicate implements Java's `Predicate<Applicant>` interface for filtered lists.

#### Design considerations:

#### Aspect: Should the filter feature be a separate command by itself?

* **Alternative 1:** Implement filter as a separate `filter -X` command.
    * Pros: May be more intuitive for new users to pick up. Can also potentially make the parsing of filter-related arguments less complicated.
    * Cons: Multiple commands doing similar things because `filter` is essentially `list` with different predicates applied to the filtered lists. Listing all data is also a predicate itself. 


* **Alternative 2 (current choice):** Implement filter as part of the `list -X` command (by taking in more parameters).
    * Pros: No two commands doing the similar things, which may lead to chunks of repeated code under the two commands.
    * Cons: May be confusing for new users, need to explain it well in user guide and help window. Also, will have to parse filter-related arguments together with other arguments in `list -X` command (such as for sorting), which may cause the parsing to be more complicated.

### Sorting of Data !NEED TO UPDATE!

#### Implementation
The implementation of sorting data is similar to list data, where sorting of different data types is done through `ModelManger`, which implements the methods in the `Model` interface.

The parsing of a sorting command from user input is also done through the 3 levels system, with `AddressBookParser`, `SortCommandParser`, and `SortXYZCommandParser` which eventually creates the `SortXYZCommand`.

#### Design considerations:

#### Aspect: How to sort data without affect the original dataset

* **Alternative 1 (current choice):** Store an additional full dataset in `ModelManager`
    * Pros: Easier to implement, less chance of error occurs when modify the displayed data.
    * Cons: Less optimal in space as we need to store a copy of the database


* **Alternative 2:** Mark an integer represent the position of the original data
    * Pros: More efficient in memory space
    * Cons: Increased the complexity of the relevant code, which make it more bug-prone.
    
--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of applicants to technology companies
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
* manage contacts faster than a typical mouse/GUI driven app
* One command and the email will be sent to all recipient
* Stores all correspondence with the candidate for easy access and viewing
* End to end seamless administration for talent management


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​     | I want to …​                           | So that I can…​                                                         |
| ------ |-------------|----------------------------------------|-------------------------------------------------------------------------|
| `* * *` | new user    | see usage instructions of all commands | know what are the commands available and how to use them                |
| `* * *` | recruiter   | add a new applicant                    | keep track of all the applicants                                        |
| `* * *` | recruiter   | delete an applicant                    | remove entries that I no longer need                                    |
| `* * *` | recruiter   | view the applicants in my contact      | access their information and contact them                               |
| `* * *` | recruiter   | view the positions I am recruiting for | know what are the positions available                                   |
| `* * *` | recruiter   | view the interviews I have             | know my schedule and plan my work day                                   |
| `* *`  | recruiter   | filter the displayed data              | find the information I am looking for easily                            |
| `*`    | expert user | access previous commands I made        | send multiple similar commands without having to type the whole command |


### Use cases

(For all use cases below, the **System** is the `HireLah Application` and the **Actor** is the `user`, unless specified otherwise)

####**Use case 01: Delete a applicant**

**MSS**

1.  User requests to list applicants
2.  HireLah shows a list of applicants
3.  User requests to delete a specific applicant in the list
4.  HireLah deletes the applicant

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. HireLah shows an error message.

      Use case resumes at step 2.

####**Use case 02: Add an interview**

**MSS**

1.  User requests to list applicants
2.  HireLah shows a list of applicants
3.  User requests to add a specific interview to a applicant in the list
4.  HireLah adds the interview to the applicant.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. HireLah shows an error message.

      Use case resumes at step 2.

#### **Use case 03: Editing position**

**MSS**
1. User requests to list positions
2. HireLah shows a list of positions
3. User chooses to edit a position based on the index from the visible list, and provide the fields to edit.
4. HireLah refreshes the list of positions to display the newly edited position.
    <br/><br/>
    Use case ends.

**Extensions**

* 3a. The given index is not a valid index in the list.
* 3a1. HireLah informs user that the index is not valid.
  <br/><br/>
  Use case ends.
  <br/><br/>
* 3b. The new position name provided is the same as another position.
* 3b1. HireLah informs user that the new position name is not valid.
  <br/><br/>
  Use case ends.

#### **Use case 04: Filtering data**

**MSS**
1. User requests to list data with filter applied.
2. HireLah refreshes the list of data to display with only data that matches the filter given.
   <br/><br/>
   Use case ends.

**Extensions**

* 1a. No data in HireLah fits the filter given.
* 1a1. HireLah informs user that no data is found.
  <br/><br/>
  Use case ends.
  <br/><br/>
* 1b. The filter type given is invalid.
* 1b1. HireLah informs user that the filter type given is invalid.
  <br/><br/>
  Use case ends.

*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. HireLah should respond within two seconds after any command is entered.
3. Should be able to hold up to 1000 applicants, positions, and interviews each without a noticeable sluggishness in performance for typical usage.
4. The data in the app should be easily transferable to another computer without losing any information.
5. A user with above average typing speed for regular English text _(i.e. not code, not system admin commands)_ should be able to accomplish most of the tasks faster using commands than using the mouse.
6. The system should be usable by a novice which has not used other CLI application for recruitment tracking.
7. A new user should be able to pick up how to use HireLah within 20 minutes of usage.
8. HireLah must boot up within 10 seconds on a device under a normal load.
9. HireLah is not required to make any direct communication with the applicants.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Role**: The type of job that the candidate is interviewing for (e.g Backend-engineer, L3 SWE)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a applicant

1. Deleting a applicant while all applicants are being shown

   1. Prerequisites: List all applicants using the `list` command. Multiple applicants in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No applicant is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
