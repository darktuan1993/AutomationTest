FROM selenium/standalone-chrome:latest

USER root
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY . /app
WORKDIR /app

RUN mkdir -p /app/baocao

CMD ["python3", "-m", "pytest", "submit.py", "--html=baocao/report.html", "--self-contained-html"]

# Chạy pytest và xuất báo cáo vào thư mục baocao
# CMD ["python", "-m", "pytest", "submit.py", "--html=baocao/report.html", "--self-contained-html"]
