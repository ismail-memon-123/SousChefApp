import requests
from bs4 import BeautifulSoup
import urllib3

# Suppress only the specific SSL warning from urllib3
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

class URLFetcher:
    @staticmethod
    def scrape_recipe(url):
        response = requests.get(url, verify=False)
        soup = BeautifulSoup(response.text, 'html.parser')
        
        recipe = {}
        
        # Get the recipe name from various possible tags
        title = soup.find(['h1', 'h2', 'h3'])
        recipe['name'] = title.text if title else 'No title found'
        
        # Try to find ingredients from various tags
        ingredients = soup.find_all(['li', 'p'], class_=lambda x: x and 'ingredient' in x.lower())
        if not ingredients:
            ingredients = soup.find_all(['li', 'p'], text=lambda x: x and 'ingredient' in x.lower())
        recipe['ingredients'] = [ingredient.get_text(strip=True) for ingredient in ingredients]
        
        # Try to find instructions from various tags
        instructions = soup.find_all(['li', 'p'], class_=lambda x: x and 'instruction' in x.lower())
        if not instructions:
            instructions = soup.find_all(['li', 'p'], text=lambda x: x and 'instruction' in x.lower())
        recipe['instructions'] = [instruction.get_text(strip=True) for instruction in instructions]
        
        return recipe


url = 'https://ashishbhatia3.wordpress.com/2015/01/21/dhabe-ka-gosht-highway-lamb-curry/amp/'
recipe = URLFetcher.scrape_recipe(url)
print(recipe["instructions"])