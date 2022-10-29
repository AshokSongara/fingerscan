import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'test',),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const channelName = 'channel';
  late MethodChannel methodChannel;

  @override
  void initState() {
    super.initState();
    methodChannel = MethodChannel(channelName);
    methodChannel.setMethodCallHandler(this.methodHandler); // set method handler
  }

  Future<void> methodHandler(MethodCall call) async {
    final String idea = call.arguments;

    switch (call.method) {
      case "installed": // this method name needs to be the same from invokeMethod in Android
      //DataService.instance.addIdea(idea); // you can handle the data here. In this example, we will simply update the view via a data service
        break;
      default:
        print('no method handler for method ${call.method}');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: ListView(
          shrinkWrap: true,
          children: [
            ListTile(
              title: const Text("Mantra"),
              onTap: () async {
                var data = await methodChannel.invokeMethod("mantraDevice");
                print("#####${data}");
                ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                  content: Text(data),
                ));
              },
            ),

          ],
        ));
  }
}