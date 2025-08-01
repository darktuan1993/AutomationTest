from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
import time

def test_login():
    # Cấu hình trình duyệt headless (không hiển thị GUI)
    chrome_options = Options()
    chrome_options.add_argument("--headless")
    chrome_options.add_argument("--no-sandbox")
    chrome_options.add_argument("--disable-dev-shm-usage")

    # Khởi tạo trình duyệt
    driver = webdriver.Chrome(options=chrome_options)

    try:
        # Mở trang login demo (có thể thay URL thật của bạn)
        driver.get("https://the-internet.herokuapp.com/login")

        # Nhập username
        driver.find_element(By.ID, "username").send_keys("tomsmith")

        # Nhập password
        driver.find_element(By.ID, "password").send_keys("SuperSecretPassword!")

        # Click nút Login
        driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

        time.sleep(2)  # chờ chuyển trang

        # Kiểm tra URL hoặc thông báo
        assert "secure" in driver.current_url
        success_msg = driver.find_element(By.ID, "flash").text
        assert "You logged into a secure area!" in success_msg

        print("✅ Test passed: Login successful!")

    except Exception as e:
        print(f"❌ Test failed: {e}")
        exit(1)

    finally:
        driver.quit()

if __name__ == "__main__":
    test_login()
