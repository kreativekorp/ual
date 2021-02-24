SRCFILES := $(shell find src -name \*.java)
PACKAGES := $(shell find src -name \*.java | sed -E s/^src\\/\|\\/[^\\/]+$$//g | sort | uniq | tr / .)

all: clean bin doc ual.jar

eclipseall: eclipseclean ual.jar

clean:
	rm -rf bin
	rm -rf doc
	rm -rf *.jar

eclipseclean:
	rm -rf *.jar

bin: src
	mkdir -p bin
	javac -source 1.5 -target 1.5 -sourcepath src $(SRCFILES) -d bin

doc: src
	mkdir -p doc
	javadoc -sourcepath src $(PACKAGES) -d doc

ual.jar: bin
	export COPYFILE_DISABLE=true
	find bin -name .DS_Store -delete
	jar cf ual.jar -C bin com/kreative/ual -C bin com/apple/eawt -C bin java/awt/desktop

.PHONY: all eclipseall clean eclipseclean
