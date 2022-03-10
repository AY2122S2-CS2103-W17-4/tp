---
layout: page
title: User Guide
---

HireLah is a desktop app that helps **recruiters to manage talent and job candidates** by tracking every step of the hiring process, 
from offering positions to scheduling interviews with candidates. It is optimised for Command Line Interface (CLI) users while still offering a GUI, so that power users can accomplish tasks much quicker by using commands


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `HireLah.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an Applicant: `appladd`

Adds a new applicant to HireLah

Format: `appladd n/APPLICANT_NAME d/DOB g/GENDER p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An applicant can have any number of tags (including 0)
</div>

* DOB provided must be in format YYYY-MM-DD
* Gender must be M/F

Examples:
* `appladd n/Benedict d/1998-02-03 g/M p/98123456 e/ben@gmail.com a/12 Kent Ridge Drive, 119243`
* `appladd n/Max d/2000-01-01 g/M p/97123456 e/max@yahoo.com a/12 Kent Ridge Drive, 119243 t/Data Analyst`

### Listing all Applicants : `appllist`

Shows a list of all Applicants in HireLah.

Format: `appllist`

### Editing an Applicant : `appledit`

Edits an existing Applicant in HireLah.

Format: `appledit INDEX [n/APPLICANT_NAME] [d/DOB] [g/GENDER] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the Applicant at the specified `INDEX`. The index refers to the index number shown in the displayed Applicant 
  list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the applicant will be removed i.e adding of tags is not cumulative.
* You can remove all the Applicant’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `appledit 1 n/Belle d/1960-03-04 g/F p/81234567` Edits the name, DOB, gender and phone number of the 1st applicant 
   to be `Belle`, `1960-03-04`, `F` and `81234567` respectively.
*  `appledit 2 e/belle@yahoo.com a/13 Computing Drive 612345 t/` Edits the email and address of the 2nd applicant to be 
   `belle@yahoo.com` and `13 Computing Drive 612345` respectively, and clears all existing tags.

### Deleting an Applicant : `appldel`

Deletes the specified Applicant from HireLah.

Format: `appdel INDEX`

* Deletes the Applicant at the specified `INDEX`.
* The index refers to the index number shown in the displayed Applicant list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd applicant in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st applicant in the results of the `find` command.

### Adding positions : `posadd`

Adds a new open position to HireLah.
Format: `posadd POSITION_NAME n/NUM_OPENINGS [d/DESCRIPTION] [r/REQUIREMENTS]`
* Positions must have a **unique name**.
* Name provided is case-insensitive.
* Number of openings in the position must be **0 or more** 0, 1, 2, …​

Examples:
* `posadd Senior Software Engineer n/3 d/More than 5 years experience r/JavaScript r/HTML r/CSS`

### Edit positions : `posedit`

Edit an existing position in HireLah.
Format: `posedit POSITION_NAME [n/NUM_OPENINGS] [d/DESCRIPTION] [r/REQUIREMENTS]`

* Edits the available position with POSITION_NAME.
* Position name provided is case-insensitive.
* At least one optional field must be provided.
* Existing attributes of the position will be updated to the input value.
* When editing requirements, the existing requirements of the position will be removed. i.e. adding requirements is not cumulative. 
* Requirements can be removed by providing an empty requirement field. i.e. r/

Examples:
* `posedit Senior Frontend Software Engineer n/5`
* `posedit Senior Frontend Software Engineer r/JavaScript r/React`

### Delete positions : `posdel`

Deletes an existing position in HireLah.
Format: `posdel POSITION_NAME`
* Existing position with the specified name is deleted.
* The position name has to match with the position that is to be deleted.
* Position name provided is case-insensitive.

Examples:
* `posdel Senior Frontend Software Engineer`

### List Positions : `poslist`

Lists all existing positions in HireLah.
Format: `poslist`

### Adding Interview : `intvwadd`

Adds a new interview to HireLah.

Format: `intvwadd n/CANDIDATE_INDEX d/DATE r/ROLE`
* Date provided must be in format YYYY-MM-DD HH:MM.
* Role *must* currently exist in position.

Examples:
* `intvwadd n/1 d/2022-01-01 14:00 r/Senior Frontend Software Engineer`

### Editing an Interview : `intvwedit`

Edit an existing interview in HireLah.

Format: `intvwedit CANDIDATE_INDEX ROLE [d/DATE] [r/NEWROLE]`
* Edits the interview with CANDIDATE_INDEX and ROLE.
* At least one optional field must be provided.
* Existing attribute of the interview will be updated to the input value.
* When editing requirements, the existing requirements of the interview will be removed. i.e. 
adding requirements is not cumulative.

Examples:
* `intvwedit 1 Senior Frontend Software Engineer d/2022-01-01 15:00`
* `intvwedit 1 Senior Frontend Software Engineer r/Senior FullStack Developer`

### Deleting an Interview: `intvwdel`

Deletes an existing interview in HireLah.

Format: `intvwdel CANDIDATE_INDEX ROLE`
* Date provided must be in format YYYY-MM-DD HH:MM. 
* Existing interview with the matching CANDIDATE_INDEX and ROLE is deleted. 
* Role provided is case-insensitive.

Examples:
* `intvwdel 1 Senior Frontend Software Engineer`

### Listing interviews: `intvwlist`

Lists all existing interviews for the candidate in HireLah.

Format: `intvwlist CANDIDATE_INDEX`

Examples:
* `intvwlist 1`

### Filter data: `filter`
View different applicants, interviews and positions in HireLah through various filters. It alters the current display of HireLah and changes the index of the relevant data.

Format: `filter DATA_TYPE b/FILTER_TYPE [ARGUMENT]`

* Different data has different filters available, thus requiring different arguments, as listed:

DATA_TYPE | FILTER_TYPE | ARGUMENT(S)           | Description
----|-------------|-----------------------|---
`appl` | `name`      | `n/KEYWORD`           | View applicants whose name contains the keyword
`appl` | `tag`       | `t/TAG1, [t/TAG2, …]` | View applicants who have all the tags specified
`intvw` | `appl`      | `n/NAME`              | View interviews for applicants whose name is specified
`intvw` | `date`      | `d/DATE`              | View interviews happening on the specified date (Date provided must be in format YYYY-MM-DD)
`pos` | `name`      | `n/KEYWORD`           | View positions which has the specified keyword in the position name

Examples:
* `filter appl name n/john`
* `filter appl tag t/school t/friend`
* `filter intvw date d/2022-03-20`

### Sort data: `sort`

Arranges applicants, interview and positions in HireLah according to their properties. 
It alters the current display of HireLah and changes the index of the relevant data.

Format: `sort DATA_TYPE SORT_ORDER`

User can specify the order of the sorted data by typing `ASC` (for ascending) 
or `DSC` (for descending) in the `REVERSE` part.

| DATA_TYPE | Sorting properties | Description                                            |
|-----------|--------------------|--------------------------------------------------------|
| `APPL`    | Name               | Sort and view applicants based on their name           |
| `INTVW`   | Date               | Sort and view interviews based on their occurring date |
| `POS`     | Name               | Sort and view positions based on the position name     |

Examples:
* `sort APPL ASC`
* `sort POS DSC`

### Viewing help: `help`

Lists all the command keywords with their general descriptions

Format: `help`

_**For a more detail description about a specific `command`, you can type in the following:**_

Format: `help COMMAND`

* Full description and format of the command will be displayed

* Command name is not case-sensitive

Examples:
* `help posadd`
* `help Intvwdel`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Upon exiting HireLah, the data in the application will automatically be saved, including the positions, applicants, and interviews. There is no need to save manually.



--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

**Q**: How do I schedule an interview for a new applicant?<br>
**A**: You will need to first create a new applicant in HireLah, and ensure that the applied position exists in the system, else you will need to create the position as well. To schedule an interview, simply create a new interview with the applicant and the position.

**Q**: Can I add an applicant without any interviews scheduled?<br>
**A**: Yes, you can simply add a new applicant in HireLah without adding any interviews.

**Q**: I have a position that is only open for one applicant, do I still have to add the position?<br>
**A**: Yes, you will need to add the position as well even if it is only used once.

**Q**: A position has been filled, what do I do with the position created in the app?<br>
**A**: You can either mark the position as not open, or delete the position from HireLah according to your requirement and preference.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HireLah home folder.

