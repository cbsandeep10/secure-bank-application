# Revisions for Secure bank

## Account details
Account no
user Name
balance
account type
interest
create date
update date


## Statement
date
description
type
amount
balance

## Requests - transaction - tier 2

## Requests - users detail modification - tier 1

## Requests - employees detail modification - admin

account type :{
    1 : Checkings,
    2 : Savings
}

user type :{
    1 : Individual
    2 : Merchant
}

Request type :{
    1 : Transaction
    2 : user detail modification
    3 : employee detail modification
}

Transaction type :{
    1 : Debit
    2 : Credit
}

Status type :{
    0 : Pending
    1 : Approved
    2 : Declined
}
