# SnackbarDemo
A simple android library to show message on the screen.
## Screenshot
<img src="img/6.gif" width="30%"> 
android 6.0
<img src="img/6.jpg" width="30%">
android 4.0
<img src="img/4.jpg" width="30%">

## Usage
### Gradle

```
dependencies {
   	
}
```

### Maven
```
<dependency>
  
</dependency>
```

## A simple CookieBar.
```
 new MSnackBar.Builder(MainActivity.this)
                        .setTitle(R.string.cookie_title)
                        .setMessage(R.string.cookie_message)
                        .setDuration(3000)
                        .setTitleSizeTextSize(25)
                        .setMessageTextSize(18)
                        .setActionTextSize(15)
                        .setBackgroundColor(R.color.colorPrimary)
                        .setActionColor(R.color.colorAccent)
                        .setTitleColor(android.R.color.white)
                        .setAction(R.string.cookie_action, new OnActionClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getApplicationContext(), "点击后，我更帅了!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
                        //.show(view);
```

## License

    Copyright 2017 Eric Liu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
