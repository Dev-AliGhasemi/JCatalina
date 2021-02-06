# JCatalina
JCatalina is a java program that run in the background to change the catalina wallpaper from morning to night

Catalina is a good wallpaper used in MacOs . this is island Catalina !

![Catalina](https://i.redd.it/m8c20gchf7231.jpg)

## What does it do ?
JCatalina work in background and check local time every 5 minutes and set appropriate wallpaper in linux .
JCatalina run using crontab app 

## How can config in linux system ?
1. rename an image(any image that you like) to Catalina.jpg and set it as wallpaper .
2. copy Catalina.jar and images directory to /usr/local/bin 
3. copy Catalina-start.sh to /usr/local/bin
4. for start JCatalina at startup should use this coammand in crontab.

now you can run it using this command :
`sh /usr/local/bin/Catalina-start.sh`

## How start JCatalina at startup ?
use these commands :
`crontab -e`

in the crontab file at the end put this command :

`@reboot sh /usr/local/bin/Catalina-start.sh`

then save the file and reboot !

## License 
JCatalina under GPLv3

## Collaborate
im enjoy to improve this app with you ! :)
take cate !
