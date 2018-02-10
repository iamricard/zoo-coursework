TARGET = out.tex
OUT_FORMAT = latex
IN_FORMAT = markdown+smart
HEADER = src/header.tex

.PHONY: all clean $(TARGET) pdf

all: $(TARGET) pdf

$(TARGET): src/*.yaml src/*.md
	pandoc \
	-H $(HEADER) \
	-f $(IN_FORMAT) \
	-t $(OUT_FORMAT) \
	-s -o $(TARGET) $^ \
	--top-level-division=chapter \
	--number-sections \
	--pdf-engine=xelatex \
	--pdf-engine-opt=-shell-escape

pdf:
	xelatex -shell-escape $(TARGET)

clean:
	-@rm -f $(TARGET) out.*
