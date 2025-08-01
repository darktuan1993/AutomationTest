FROM python:3.10-slim

# Cài Chromium và driver
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        chromium-driver chromium && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Copy và cài requirements
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copy source code vào thư mục /app
COPY . /app/.
WORKDIR /app

# Biến môi trường cho Chromium
ENV CHROME_BIN=/usr/bin/chromium
ENV PATH="${PATH}:/usr/lib/chromium/"

# Tạo thư mục chứa báo cáo
RUN mkdir -p /app/baocao

# Chạy pytest và xuất báo cáo vào thư mục baocao/
CMD ["python", "-m", "pytest", "submit.py", "--html=baocao/report.html", "--self-contained-html"]

