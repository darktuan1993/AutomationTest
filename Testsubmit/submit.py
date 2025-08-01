import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
import time

@pytest.fixture(scope="module")
def driver():
    options = Options()
    options.add_argument("--headless")
    options.add_argument("--no-sandbox")
    options.add_argument("--disable-dev-shm-usage")

    service = Service("/usr/bin/chromedriver")
    driver = webdriver.Chrome(service=service, options=options)

    driver.get("https://demoqa.com/text-box")

    # ✅ Bỏ iframe quảng cáo gây chặn click
    driver.execute_script("""
        const iframes = document.getElementsByTagName('iframe');
        for (let i = 0; i < iframes.length; i++) {
            iframes[i].remove();
        }
    """)

    yield driver
    time.sleep(1)
    driver.quit()

def test_fill_form(driver):
    driver.find_element(By.ID, "userName").send_keys("Test User")
    driver.find_element(By.ID, "userEmail").send_keys("testuser@example.com")
    driver.find_element(By.ID, "currentAddress").send_keys("123 Main St")
    driver.find_element(By.ID, "permanentAddress").send_keys("456 Permanent Ave")

    # ✅ Cuộn xuống và click bằng JS (an toàn)
    submit_button = driver.find_element(By.ID, "submit")
    driver.execute_script("arguments[0].scrollIntoView(true);", submit_button)
    driver.execute_script("arguments[0].click();", submit_button)

    output_name = driver.find_element(By.ID, "name").text
    output_email = driver.find_element(By.ID, "email").text

    assert "Test User" in output_name
    assert "testuser@example.com" in output_email
