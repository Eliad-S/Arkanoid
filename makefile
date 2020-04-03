# 319203162 
# selleme
compile: bin
	find src -name "*.java" > sources.txt
	javac -cp biuoop-1.4.jar:.src -d bin @sources.txt

jar:
	jar -cfm ass7game.jar manifest.mf -C bin . -C resources .
	
run:
	java -cp biuoop-1.4.jar:resources:bin arkanoid/game/Ass7Game

bin:
	mkdir bin