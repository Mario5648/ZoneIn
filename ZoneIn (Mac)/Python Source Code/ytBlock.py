import requests
from bs4 import BeautifulSoup

""""""""""""""""
Function:
    decide() -This function will load the full page
              of the specific youtube video given by
              the user. Then it will find the meta that
              contains the content data and then extract
              the data to compare. If the category is
              Entertainment then block

"""""""""""""""""



def decide(URL):
    url = URL
    data = requests.get('https://www.youtube.com/watch?'+str(url))
    soup = BeautifulSoup(data.text, 'html.parser')
    category = soup.find(itemprop="genre").get("content")
    if str(category) == "Comedy" or str(category) == "Film & Animation" or str(category) == "Pets & Animals" or str(category) =="Sports" or str(category) == "Travel & Events" or str(category) == "Gaming" or str(category) == "People & Blogs" or str(category) == "Entertainment" or str(category) == "Howto & Style":
        return 1
    return 2

    #This is how the meta looks like
    #<meta itemprop="genre" content="Gaming">
