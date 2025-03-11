* to execute and run test cases

  mvn clean install exec:java -Dexec.mainClass="mainapp.MyApp" -DskipTests=true

git init
create utils folder
create Assessment.txt file inside utils
add line "This is the first line." in Assessment.txt
add line "This is the second line." int Assessment.txt
git add .
git commit -m "Added second line"
git revert HEAD --no-edit
create utils folder
create Assessment.txt file inside utils
add line "This is the third line." in Assessment.txt
git add .
git commit -m "Added third line"
