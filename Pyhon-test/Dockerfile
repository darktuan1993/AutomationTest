FROM python:3.10-slim

# Cài các dependencies cần thiết và làm sạch để giảm size
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        chromium-driver chromium && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Copy và cài requirements
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copy script và cấu hình môi trường
COPY login-check.py /app/login-check.py
WORKDIR /app

ENV CHROME_BIN=/usr/bin/chromium
ENV PATH="${PATH}:/usr/lib/chromium/"
CMD ["python", "login-check.py"]