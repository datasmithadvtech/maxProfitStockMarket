# maxProfitStockMarket
<br>

```
Each day the stock price fluctuates during the day.
Given an array of stock prices, what would be the most
efficient way to determine the best time to buy and sell
to get the max profit. You must buy before you sell.

Examples:
--------------------------------------------------------------------------

maxProfit( [ 1, 3, 5, 4, 3, 5, 10, 9, 8, 4, 9]) ->  buy at 1, sell at 10
maxProfit( [ 2 , 3, 1, 5, 6, 9, 10, 15 14 ]) -> buy at 1, sell at 15
maxProfit( [ 15, 12, 11, 9, 7, 5, 3, 1] ) -> no profit is possible
```
<br>

## Program Overview
<p float="left">
  <img src="https://github.com/sanderhelleso/maxProfitStockMarket/blob/master/maxProfit/screenshots/maxProfit1.jpg" alt="app img 1" width=400 height=600 />
  <img src="https://github.com/sanderhelleso/maxProfitStockMarket/blob/master/maxProfit/screenshots/maxProfit2.jpg" alt="app img 2" width=400 height=600 />
</p>
<br>

## Program Description
```
This application reads an array of generated data of user defined size that 
simulates the stock market. It then determinesthe best time to sell / buy 
to recieve the best potensial profit available of the data.

After generating the result, the application creates a new report CSV file and filled
it up with data from the calculations (id, sellValue, buyValue, sellDay, buyDay)

A nice addition to this application could be to generate a chart using the freshly
created CSV file to further visually display the data.
```
