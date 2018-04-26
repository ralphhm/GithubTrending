# GithubTrending
### Summary
This is a sample app that shows trending Android repositories on Github.
The main screen which is the entry point of the app is a list repositories. It acts as master view where the user can click on a repository item and gets navigated to a detail view.
### Dependencies
* Kotlin
* Retrofit 2
### Questions
#### How is trending Github repositories defined?
Use [Github Trending Website](https://github.com/trending) as reference:
Trending repositories seem to be defined as these repositories that gained stars in certain time interval:
* Today
* This week
* This month

From the naming it seems like the stars per repository is counted from the beginning of the interval until now.
This translates into the following pseudo query: Return all repositories that gained stars since the beginning of [interval] sorted by stars gained in descending order.
Browsing the documentation of [Github REST API v3](https://developer.github.com/v3/) there is no API call that returns a list of repositories that match the criteria or returns a result similar to [Github Trending Website](https://github.com/trending).
This leads to the following alternatives
1. Parse the html output of [Github Trending Website](https://github.com/trending)
2. Use a third party library/API that parses the [Github Trending Website](https://github.com/trending) like [trending-github](https://github.com/ecrmnn/trending-github)
3. Use a [suggested API call on stackoverflow](https://stackoverflow.com/questions/30525330/how-to-get-list-of-trending-github-repositories-by-github-api) using the Search Repository API
4. Simplify the requirement by using "popular" instead of "trending" repositories

Solution one is a lot of work to implement and is unstable meaning that it could completely break the app when the page is changed. Solution two makes the App dependent on an external source that is out of control. It's also not provided in a way that cann be easily used by or integrated into the App. Solution three doesn't meet the requirements as the stars are not counted based on a time interval. Solution four is a compromise that is near to the requirement, easy to implement and should word as long as API v3 is supported.
#### How to get a list of popular Github repositories from the Github API
Use the [Github search repositories API](https://developer.github.com/v3/search/#search-repositories) using the following parameters
* Filter by topic android
* Sort by stars
* Order descending

This results in the following API call:
```
https://api.github.com/search/repositories?q=topic:android&sort=stars&order=desc
```
#### What kind of information should be shown in a single repository item?
Use [Github Trending Website](https://github.com/trending) as reference:
* image of the owner
* full name (includes owner name)
* description
* \# of stargazers
* \# of forks

The Language property is left out as Android repositories are either Java or Kotlin.
#### How to present the repository items?
The first question that arises is how to present the items on the screen next to each other. Following Design Guidelines we have three main representations:
* Grid lists
* Cards
* List

The collection of repositories that needs to be visualized is a homogeneous data type thus the [list representation](https://material.io/guidelines/components/lists.html) is chosen.
To visualize the contents of the repository tile/item a two line list with avatar is chosen.