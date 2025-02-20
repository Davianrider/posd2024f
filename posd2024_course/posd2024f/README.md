## Pattern Oriented Software Design, Fall 2024
- Instructor: Prof. Y C Cheng
- Class meeting time: Mon 7-8-9
- Class meeting place:
  - Hong-Yue technology Research Building-1222&1223 (in person)
  - [Microsoft Teams](https://teams.microsoft.com/l/team/19%3AoQBubdF152LO2WVrJMRHVX7rOx0wb_v4twMSorq8Va81%40thread.tacv2/conversations?groupId=f0cff185-d612-415d-8bf9-76ed37baeee0&tenantId=dfb5e216-2b8a-4b32-b1cb-e786a1095218)
- Office hours: Tue 2-3-4, Thu 2-3-4
- Course Repo: Course repository: http://140.124.181.100/yccheng/posd2024f
- TA: 
  - Benny Wang \<benny870704@gmail.com\>, Regina Yen\<ycycchre@gmail.com\> (Room 1324)
  - Office hours: 10-12 am, Wed
- TA's homework repository: [posd2024_TA](http://140.124.181.100/course/posd2024f_ta)

### Synopsis 

This course aims to build a foundation for students to build software with _design patterns_ and mainstream _programming paradigms_ used in modern software development. We will also cover the SOLID principles of object-oriented design. These topics will be threaded in two to three long-running examples developed in the classroom with participation from students. 

Design patterns examine reusable solutions to object-oriented design problems (maintainability, extensibility, etc.) in software developed in object-oriented languages like C++, Java, Python, Ruby, OCaml and so on.

  - design patterns
    - individual patterns by category as they are encountered in the running example
    - application of multiple patterns
    - real-world examples

The programming paradigms affect how design patterns are implemented. Although object orientation is the main paradigm, other paradigms that contribute to a cleaner implementation are examined in the context of design patterns implementation.

  - Programming paradigms
    - procedure (ad hoc polymorphism, c, python)
    - function (as in math functions, main abstraction in lisp, scheme, haskell, ...)
    - generics and templates (static polymorphism)
    - object orientation (dynamic polymorphism, in all object-oriented languages such as C++, Java, Ruby, Python, ...)
    - static typing vs dynamic typing

SOLID principles examine object-oriented design principles behind object-oriented software design, patterns and beyond, to achieve _separation of concerns_. We will look at source level principles and review of their use in the running example

The main part of lectures is threaded with a long-running example involving the creation, manipulation, and storage of geometric shapes. Development of the example is done mostly in class meetings; it is done iteratively and incrementally and with test-driven development, mob programming, and continuous integration. Source code is available to class participants through a repository.

  - Problem solving with _How To Solve It_ (HTSI):
    - _understanding the problem_, _devising a plan_, _carrying out the plan_, and _looking back_
  - Engineering practices
    - mob programming with strong style pair programming
      - class participants will take turn playing the roles of _driver_ and _navigator_
    - code proceeded by tests: _failing test-passing test-refactoring_ _TDD_
      - google test 
    - source control through git
    - program builds through builders and continuous integration

## Homework, exams, and grading policy
  - Homework*4: 20%
  - Midterm (Lab test): 20%
  - Term project: 30%, details to be announced later
  - Final (Pen-and-paper test): 30%
## Textbook
  - Gamma, Erich, et al. Design patterns: elements of reusable object-oriented software. Pearson Education, 1994.
## Reference books:
  1. Martin, Robert C. Clean code: a handbook of agile software craftsmanship. Pearson Education, 2008.
  2. Alexander, Christopher, Sara Ishikawa, and Murray Silverstein. A pattern language. Oxford University Press, 1977 
  3. Alexander, Christopher, The timeless way of building, Oxford University Press, 1979  

## Two exercise problems

** Review Problem 1 (Homework 1): sorting shapes**
A simple geometry application called _geo_ is needed to sort shapes such as triangles, circles, rectangles and others. As a command line application, _geo_ reads shapes from an input file, sorts the shapes by area or perimeter in increasing order or decreasing order, and write the result to an output file. For example,
```
geo input.txt output.txt area inc
```
sorts the shapes in file _input.txt_ in increasing order by area, and writes the result to the file _output.txt_. And
```
geo input.txt output.txt perimeter dec
```
sorts the shapes in file _input.txt_ in decreasing order by perimeter, and writes the result to the file _output.txt_.

** Practice Problem 1: Shape manipulation**
A program for manipulating geometric shapes is needed. With the program, the user creates/deletes/modifies simple shapes such as square, circle, triangle, and convex polygons. Individual shapes will have properties such as area, perimeter, color and so on. Compound shapes can be formed from individual shapes. Area and perimeter properties are still needed on compound shapes. A compound shape can include other compound shapes. The following operations on shapes are needed.

- Add shapes to a compound shape;
- Delete shapes from a compound shape;
- Group and ungroup;
- Move shapes out of a compound shape;
- Move shapes from a compound shape to another compound shape;
- Select shapes by Boolean expression on the properties area, perimeter, color and type; and
- Load/store shapes from/into a file.

**Lectures**

Week 1, 9/9/2024
- overview 
- where do patterns come from
- what is a pattern
  - patterns of events: watching the world go by
  - patterns of space: Private Terrace on the Street (140), Street Windows (164), Window Place (180)

Week 2, 9/16/2024
- A note application
  - What are the patterns of events?
  - What are the patterns of space?

**reading** 
- Composite pattern (for Content)
- Iterator pattern (for accessing contents inside a hybrid content)

Week 3, 9/23/2024

How structures of space supports the patterns of events
  - our note app supporting linear note
  - a note consists of a sequence of units of contents
  - a unit of content can be a simple content like Text, or 
  - it can be a composite content like HybridContent
  - contents inside a HybridContent are accessed through an iterator
  - a Text object can return a NullIterator (hasNext() always returns false)

**reading**

- Decorator pattern (for CaptionedContent)
- Visitor pattern for adding operations without changing the Content hierarchy

Week 4, 9/30/2024

- adding Picture
- captioned contents

Week 5, 10/7/2024

- wordCount with Visitor
- insert additional content to a content 
  - content = text:
    - insert text to text -> text with text inserted 
    - insert pic to text -> three contents: part of text before current location, pic, part of text from current location
    - insert hybrid to text -> three contents: part of text before current location, hybrid, part of text from current location

- Reading:
  - Java checked exception and unchecked exception

Week 6, 10/14/2024

- review of word counting visitor
- sequence diagram: double dispatch
- find occurrences of the string "world", set location of the Text there, and insert "wonderful " 
- what are the properties of a content so far as "find" and "insert" or "replace" is concerned?
  - the current position in a content: user is "moving" through the content, where is it?
    - Text: 0, n-1
    - Picture: 0, 1 
    - Captioned Content:
      - Content
      - Caption: 0, n-1
    - Hybrid Content:
      - current component position
      - position inside current component
  - the position for "not found"
    - current position = -1
    - alternative position "most recent find position"??

Week 7, 10/21/2024

Visitor, continued
  - Find? Actually, find string and set position  
  - Find content, return "pointer" to found instance
    - findfirst
    - findall

What is a _responsibility_ of an object?
  - knowing 
    - its own data
    - its relationships  
  - doing
    - operations related to knowing
      - adding and deleting
      - accessing
    - computing
    - delegating
    - collaborating

Example: textbook
  - Composite
  - Table 2.1

What are the responsibilities of a content?

What are the responsibilities of a visitor?

How do they collaborate?

Open-closed principle


Week 8, 10/28/2024 **Midterm exam week**

Week 9, 11/04/2024

- OCP applied to Composite and Visitor
  - strategic closure

- Exercise: Persisting a note to a file with Visitor

- Scanner-Parser-Builder

Week 10, 11/11/2024
Patterns: 
- Command
- Singleton (including implementation)
- Template Method
- GoF Table 1.1 Design pattern space

Week 11, 11/18/2024
JUnit: A cook's tour
- Command, Template Method, Collecting Parameters, Adapter, Pluggable Selector, and Composite

Week 12, 11/25/2024
JUnit: A cook's tour: implementation I
- Command, Template Method, Collecting Parameters

Week 13, 12/2/2024
JUnit: A cook's tour: implementation II
- Adapter, Pluggable Selector, and Composite

Week 14, 12/9/2024
- OCP, covered
- Dependency Inversion principle (DIP)
  - Facade (GoF)
  - Adapter
- Liskov Substitution Principle (LSP)
  - relation to contracts
- Single Responsibility Principle (SRP) 
- More design patterns
  - Observer
Week 15, 12/16/2024
- More design patterns
  - Strategy
    - __Program to interface, not an implementation.__
    - __Favor object composition over class inheritance.__
      - dynamic (runtime) vs static (compile time)
      - sum over product 
    - compare Decorator, Template Method
  - Proxy 
    - remote, virtual, protection, smart pointer
    - compare Decorator, Adapter
- Conclusion
  - Table 1.1, Table 1.2, and Figure 1.1
  - Common causes of redesign

Week 16, 12/23/2024 **Final exam week**

Week 17, 12/30/2024 **Term project presentation**

Week 18, 1/6/2025   **Term project presentation**
