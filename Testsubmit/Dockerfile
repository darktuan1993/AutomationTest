FROM selenium/standalone-chrome:latest

USER root
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY . /app
WORKDIR /app

RUN mkdir -p /app/baocao

CMD ["python3", "-m", "pytest", "tests/test_form.py", "--html=baocao/report.html", "--self-contained-html"]
