Instructions taken from online course DEV276x: Learn to Program in Java

___

# Module 1 Project - Trip Planner
For this project, you are going to write a program that asks the user for some information about an international trip they are taking. Based on that information you will need to do some conversions, using the correct data types, to tell them some information to help them plan their trip. Here is my sample output:

```
Welcome to Vacation Planner!

What is your name? Kasey Champion
Nice to meet you, Kasey Champion. Where are you travelling to? Mexico City
Great! Mexico City sounds like a great trip!
***********

How many days are you going to spend travelling? 14
How much money, in USD, are you planning to spend on your trip? 2300
What is the three letter currency symbol for your travel destination? MXC
How many PHP are there in 1 USD? 19.8

If you are travelling for 14 days that is the same as 336 hours or 20160 minutes.
If you are going to spend $2300 USD, that means per day you can spend up to $164.28 USD.
Your total budget in MXC is 45540.0 MXC, which per day is 3252.85 MXC
***********

What is the time difference, in hours, between your home and your destination? 2
That means that when it is midnight at home, it will be 2:00 in your travel destination, and when it is noon at home it will be 14:00.
***********

What is the square area of your destination country in km2? 1973000
In miles2 that is 761775.3
***********
```

For your first project, we are going to break up the work into different parts. You’ll want to think about the process of development when approaching any project. As the course progresses you’ll be expected to do more of this planning yourself, but for now pay attention to how we break the complex problem down into smaller, easier to tackle, pieces.

### Part 1 – Greeting
First, you’ll want to greet your user and ask them their name. As you can see above the user types their response *on the same line* as the question. To do this you’ll want to use a System.out.print() statement instead of a System.out.println() statement. Once your user tells you their name use it to greet them! Then ask them where they would like to go. Finally, tell your user their destination sounds like a great trip. All the code to accomplish Part 1 should be in its own method.

**HINT:** Some names and destinations are more than 1 word! Plan accordingly.

### Part 2 – Travel time and Budget
For this part, you’ll want to ask the user about how much time and money they are budgeting for their trip. Ask the user:

How many days are they going to spend in their destination?
What is their total budget for the trip in USD?
What is the currency symbol for their destination? For example, the us dollar’s is USD, the euro’s is EU etc…
The conversion rate between 1 USD to however many of that destinations currency. For example, there are 0.9 euro to 1 usd, so the user would type in 0.9
Once you have all this info (stored in the correct variable types), use it to tell the user the following information:

How much time the user will spend at their destination in days, hours, minutes and seconds
Show the user their budget in USD for the whole trip and how much they can spend in USD per day
Show the user their budget in the travel destination’s currency for the total trip and per day
All the code to accomplish Part 2 should be in its own method.

**HINT:** keep in mind that if you divide integers you might lose some information when it truncates.

### Part 3 – Time Difference
For this part, you’ll need to ask the user about the time difference between their home and where they are going. If the destination time zone is “behind” the user’s home time zone the user should enter a negative number. For example, the time difference between Seattle and Nairobi is 9 hours, and the time difference between New York and Mexico City is -1 hours.

Show the time it will be in the travel destination when it is midnight at home and when it is noon at home. You can report these in 24 format, where midnight is 0:00 and noon is 12:00.

**HINT:** Your answers might be greater than 24. Try using the % or mod operator to "wrap around" to 24-hour time format. For example, 12 % 24 is 12, but 25 % 24 is 1.

All the code to accomplish Part 3 should be in its own method.

### Part 4 – Country Area
Only three countries in the world us the "imperial system", so most countries report their distances in kilometers. For this part, ask the user the area of their travel destination country in km<sup>2</sup>. Then you’ll want to convert that to miles<sup>2</sup> and report those results back to the user.

All the code to accomplish Part 4 should be in its own method.

### Part 5 – Round
You may have noticed some of our math has produced numbers with lots of decimal places, making then not look very nice. In this part, you need to go back and clean up those answers so they each only have 2 decimal places.

To do this you are going to use a combination of ints and doubles.

First, multiply the number you want to reformat by 100.
Cast the result of part 1 to an int like so:

`(int) answerFromOne`

This will drop any decimal places 3. Convert your answer back to a double, and move the decimal place over by 2 numbers like so:

`answerFromTwo / 100.0`

If you follow the above steps you should find that any of your answers are not limited to only 2 decimal places, however, those decimal places won’t be rounded, they will just be the first two from the original answer.

This code will need to be added to each of the methods you wrote for parts II, III and IV.



---

*(This part is not required to complete the course and not implemented in this repository)*

### Part 6 – Hacker Problem - How Far?

For extra fun, calculate the distance between the user’s home and their travel destination using the Haversine formula. https://en.wikipedia.org/wiki/Haversine_formula To do this you’ll need to ask the user to enter the longitude and latitude for their home and their travel destination. You’ll also need to do some pretty fancy math.

To do fancy math in Java (cos, sin, squared etc…) you can use the Math Class. Here is the documentation for that: https://docs.oracle.com/javase/7/docs/api/java/lang/Math.html All the code to accomplish the hacker problem should be in its own method.
