import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
import time

@pytest.fixture(scope="module")
def driver():
    driver = webdriver.Chrome()
    driver.get("https://demoqa.com/text-box")
    driver.maximize_window()
    yield driver
    time.sleep(2)
    driver.quit()

def test_fill_form(driver):
    # Điền vào form
    driver.find_element(By.ID, "userName").send_keys("Test User")
    driver.find_element(By.ID, "userEmail").send_keys("testuser@example.com")
    driver.find_element(By.ID, "currentAddress").send_keys("123 Main St")
    driver.find_element(By.ID, "permanentAddress").send_keys("456 Permanent Ave")

    # Click Submit
    submit_button = driver.find_element(By.ID, "submit")
    driver.execute_script("arguments[0].click();", submit_button)

    # Kiểm tra kết quả đầu ra
    output_name = driver.find_element(By.ID, "name").text
    output_email = driver.find_element(By.ID, "email").text

    assert "Test User" in output_name
    assert "testuser@example.com" in output_email
