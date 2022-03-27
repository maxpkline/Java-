-- use mkline;

-- Query 1
select personId, firstName, lastName from Persons;

-- Query 2
select p.personId, p.firstName, p.lastName, a.street, a.city, a.state, a.zipcode, a.country from Persons p 
left join Address a on a.personId = p.personId;

-- Query 3
select emailId, email from Email where personId = 5;

-- Query 4
update Email set email = "BillyMads26@bingo.aol" where emailId = 6;

-- Query 5
delete from AccountAsset where accountId = 1;
delete from Account where personId = 7;
update Account set managerId = 2 where managerId = 7; -- we assume he passes off management of any asset to a colleageue (cannot have NO manager)
update Account set beneficiaryId = null where beneficiaryId = 7;
delete from Email where personId = 7;
delete from Address where personId = 7;
delete from Persons where personId = 7;

-- Query 6 
select * from AccountAsset where accountId = 2;

-- Query 7
select Persons.*, count(Account.personId) as numAccounts from Persons 
left join Account on Persons.personId = Account.personId group by Persons.personId;

-- Query 8
select Persons.*, count(Account.managerId) as numAccountsManaged from Persons 
left join Account on Persons.personId = Account.managerId group by Persons.personId;

-- Query 9
select Account.*, count(AccountAsset.accountId) as numAssets from Account 
left join AccountAsset on Account.accountId = AccountAsset.accountId 
group by Account.accountId;

-- Query 10 
select Account.*, sum(AccountAsset.dividendTotal + (AccountAsset.assetQuantity * AccountAsset.assetCost)) as stocksTotal from Account
left join AccountAsset on Account.accountId = AccountAsset.accountId 
group by Account.accountId;

-- Query 11
select sum(AccountAsset.dividendTotal + (AccountAsset.assetQuantity * AccountAsset.assetCost)) as stockTotalForAllAccounts from Account
left join AccountAsset on Account.accountId = AccountAsset.accountId;

-- Query 12
select Account.*, count(AccountAsset.assetId) as numberOfPropertyPerAccount from Account 
left join AccountAsset on Account.accountId = AccountAsset.accountId group by AccountAsset.assetId 
having numberOfPropertyPerAccount > 1;

-- Query 13
select Account.*, count(AccountAsset.assetPurchaseDate < AccountAsset.optionStrikeDate) as possibleFraudulentAssets from Account 
left join AccountAsset on Account.accountId = AccountAsset.accountId group by AccountAsset.assetId having possibleFraudulentAssets>0;

 
