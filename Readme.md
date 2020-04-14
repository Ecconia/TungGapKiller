# TUNG Gap Killer

A small little Java tool, which you can feed your .tungboard files and it gets rid of all odd values, as in - it rounds them to valid values which then hopefully won't get corrupted by TUNG anymore.

Make your builds smooth like a baby butt!

## Useage:

It is a commandline tool, you should place it into your boards folder.

Run `java -jar TungGapKiller.jar <board-file-name>`. This won't overwrite your file, it will add `-rounded` at the end.

Run `java -jar TungGapKiller.jar -o <board-file-name>` to overwrite your .tungboard file with a rounded version.

## Compile:

Use the release page, or compile it yourself:
- Install maven
- Clone this repository
- Run `mvn package` in the repository folder


## Why? (Story...)

The Ultimate Nerd Game is doesn't have any form of rounding, and due to floating point math errors, or maybe other bugs, sometimes values are quite off to valid values, TUNG ignores these and happily renders ugly builds. If you have such a build, export the board, run thru this tool, import again - be happy.

If someone is able to, make this a mod :)

Everyone is free to use this, if he/she doesn't hate Java :P

## Compile (Build from source...)

- Clone the project from Github to any folder you like.
- Run command `mvn package`, you need to have Maven installed for that.
- Use the resulting .jar file `java -jar target/TungGapKiller-1-jar-with-dependencies.jar` with a Java with at least version 8. Feel free to rename the file to whatever you like.

## Collaborate/Contact

Everyone who used TUNG before may collaborate. Personally I also require such a person to be a part of the TUNG/LW community, see their [Discord](https://discord.gg/C5Qkk53). You may always contact me there, if you wish to improve something or just to talk/discuss about this project.
