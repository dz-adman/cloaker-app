# CLOAKER-APP

## About
This is a link cloaking tool created on Spring-Boot that provides various features for the user to control / personalize / filter audience visiting any of his/her link.

(This project is made on Spring-Boot 2 using java 14)

[ How it Works? ](#howItWorks)
## Project Features

- [ Device based filter ](#deviceBasedFilter)
- [ Location based filter ](#locBasedFilter)
- [ Advanced location filter (incomplete/missing location info detection) ](#advLocBasedFilter)
- [ Proxy / Vpn check filter ](#proxyVpnFilter)
- [ Referer based filter ](#refererFilter)
- [ Referer Spoofing ](#refererSpoofing)
- [ Tag User (to identify recurring user) ](#tagUser)
- **Ip Reputation Check** : This feature maybe added in future release.
- [ Reports ](#reports)
## Features in Brief
<a name="deviceBasedFilter"></a>
- ##### Device based filter
  This filter allows user to filter audience based on the device they are using. (Ex: Mobile, Tablet, Computer, Other Device, Browser, OS)
<a name="locBasedFilter"></a>
- ##### Location Based Filter
  This filter allows user to filter audience based on their worldwide location. (Ex: Audience from specific Continents, Countries, States and Cities can be restricted or allowed)
<a name="advLocBasedFilter"></a>
- ##### Advanced Location Filter
  Using this filter user can reject audience in case such as fake location where any of the location information (like country or city or longitude or latitude or anything else) maybe detected missing. It adds an extra layer of CHECK for location.
<a name="proxyVpnFilter"></a>
- ##### Proxy / Vpn check filter
  This filter can be used to deck audience using vpn or proxy.
<a name="refererFilter"></a>
- ##### Referer based filter
  User can filter out audience coming from specific sources / websites using this filter.
<a name="refererSpoofing"></a>
- ##### Referer Spoofing
  This feature can be used for [referer spofing](https://en.wikipedia.org/wiki/Referer_spoofing).
<a name="tagUser"></a>
- ##### Tag User (to identify recurring user)
  This feature can be used to set a cookie on audience's end so that if same audience comes back again, it can be detected and accepted or rejected straight away without executing whole filtration process again.
<a name="reports"></a>
- ##### Reports
  This application also provides detailed report for every hit where user can see info about all the filters and their result.

<a name="howItWorks"></a>
### How it Works?
  **Step 1** : User need to create a **RuleSet** (a set of rules/filters) that are to be applied for detection / filtration of audience.  
  **Step 2** : Then user need to create a Campaign with the RuleSet Id to associate RuleSet with it.  
  **Step 3** : Hit '/home' endpoint with url, and let the magic begin 💫 .
  
### Third party api used
  [ Abstract Api ](https://www.abstractapi.com/)
