import 'package:flutter/material.dart';
import 'package:json_holder_flutter/utils/log_util.dart';

class KeepAliveWrapper extends StatefulWidget {
  const KeepAliveWrapper({
    Key? key,
    this.keepAlive = true,
    required this.child,
  }) : super(key: key);
  final bool keepAlive;
  final Widget child;

  @override
  _KeepAliveWrapperState createState() => _KeepAliveWrapperState();
}

class _KeepAliveWrapperState extends State<KeepAliveWrapper>
    with AutomaticKeepAliveClientMixin {

  @override
  void initState() {
    super.initState();
    int startTime = DateTime.now().millisecondsSinceEpoch;
    LogUtil.d("start time = ${startTime}");
    WidgetsBinding.instance.addPostFrameCallback((Duration duration ) {
      int endTime = DateTime.now().millisecondsSinceEpoch;
        LogUtil.d("end time = ${endTime}" );

        int gap = endTime - startTime;
        LogUtil.d("gap = ${gap}");
    });
  }


  @override
  Widget build(BuildContext context) {
    super.build(context);
    return widget.child;
  }

  @override
  void didUpdateWidget(covariant KeepAliveWrapper oldWidget) {
    if(oldWidget.keepAlive != widget.keepAlive) {
      // keepAlive 状态需要更新，实现在 AutomaticKeepAliveClientMixin 中
      updateKeepAlive();
    }
    super.didUpdateWidget(oldWidget);
  }

  @override
  bool get wantKeepAlive => widget.keepAlive;
}