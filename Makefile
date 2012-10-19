CC=javac
RUN=java
SRC=src
FILE=Game
all:
	@cd $(SRC); \
	echo "Compiling $(FILE).java.."; \
	$(CC) $(FILE).java; \
	echo "Compiled $(FILE).java successfully!\n"; \
	$(RUN) $(FILE)
