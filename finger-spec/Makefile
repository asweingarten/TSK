BIBTEX = bibtex
LATEX = pdflatex
REPORT = report
RM = rm
TEMP = *.aux *.bbl *.blg *.log *.out *.toc

.PHONY: all clean

all:
	$(LATEX) $(REPORT)
	$(BIBTEX) $(REPORT)
	$(LATEX) $(REPORT)
	$(LATEX) $(REPORT)

clean:
	$(RM) $(TEMP)
