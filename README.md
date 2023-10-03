![](images/cricimage1.jpg?raw=true)
(Image Source: Google Images)

# CricHere
Now get score of live matches as a notification. Don't need to go anywhere, just do your work and get updated with the live score.

## Getting Started
CricHere is made in Java using jsoup library and REST API. CricHere will show you a small notification of live cricket score. The advantage of using CricHere is that you can continue with the work which you are doing and side by side you will get updated with the world of cricket. 

### Prerequisites
You must have JDK installed in your system to run this project. I used NetBeans on Windows to make it. However, you can run the project from command line also.

### Download
Download the jar file, and you don't need IDE (though java is needed) to run the project.
[Download](https://drive.google.com/file/d/0BwiWKez_FI_hcHpnVmkzNXdGZHM/view)

### Installation
1. Download the copy of project in your system.
2. Run the project in any IDE. If you don't use one, then follow the steps below.
2. SET the environment variables if you didn't set them till now.
3. Move to src/Test.java and run it with command 
`javac Test.java`
4. Now use command
`java Test`
to run the file.

### Choose your Country
You can set your country or any other country name to get their live score. Just go to the line number 21 of src/Test.java file and set the CNAME variable with your country name. 

## API used
API used is CricAPI. Know more about it at - http://www.cricapi.com/

## Developers
You need to change the API key by making account on the site [CricAPI](http://www.cricapi.com/) and use your API key in the project.

## Libraries Used
* [jsoup](https://jsoup.org/download) - Download and read about jsoup here

## Contributing
Feel free to contribute. I am always looking for your pull requests and I want you to help me in making this program bigger and better.

## License
This project is licensed under the GPU License v3.0 - see the [LICENSE](LICENSE) file for details.

## TO-DO List
1. Support for Linux system.  [ DONE ]
2. Make a JAR file. [ DONE ]
3. Create a button to launch score in UI.
4. Remove 10 sec time interval at the beginning. 

## Screenshots
![](images/InputIMAGE.png?raw=true)   

Windows -    

![](images/toolbar2.png?raw=true)
![](images/desktop3.png?raw=true)   

Ubuntu -    

![](images/ubuntu-notif-crichere.png?raw=true)
