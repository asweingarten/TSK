REPORT = specification.tex
RM = rm
INTERMEDIATE = *.aux *.lof *.log *.toc
LATEX = pdflatex

.PHONY: all clean

all:
	$(LATEX) $(REPORT)
	$(LATEX) $(REPORT)

clean:
	$(RM) $(INTERMEDIATE)
