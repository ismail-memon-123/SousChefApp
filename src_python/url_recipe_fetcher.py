import requests
from recipe_scrapers import scrape_html


url = "https://www.allrecipes.com/recipe/158968/spinach-and-feta-turkey-burgers/"
html = requests.get(url).content
scraper = scrape_html(html, org_url=url)
print(scraper.ingredients())


