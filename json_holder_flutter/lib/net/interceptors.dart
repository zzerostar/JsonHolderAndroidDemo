import 'package:dio/dio.dart';
import 'package:json_holder_flutter/utils/log_util.dart';

class LoggerInterceptor extends Interceptor {

  @override
  void onError(DioException err, ErrorInterceptorHandler handler) {
    final options = err.requestOptions;
    final requestPath = '${options.baseUrl}${options.path}';
    LogUtil.e('${options.method} request ==> $requestPath');
    LogUtil.d('Error Type: ${err.error} \n'
        'Error message: ${err.message}');
    handler.next(err);
  }

  @override
  void onRequest(RequestOptions options, RequestInterceptorHandler handler) {
    final requestPath = '${options.baseUrl}${options.path}';
    LogUtil.d('${options.method} request ===> $requestPath');
    handler.next(options);
  }

  @override
  void onResponse(Response response, ResponseInterceptorHandler handler) {
    // LogUtil.d('StatusCode: ${response.statusCode} \n'
    //     'Status Message: ${response.statusMessage} \n'
    //     'Headers: ${response.headers} \n'
    //     'Data: ${response.data}');
    handler.next(response);
  }
}

class AuthorizationInterceptor extends Interceptor {

  @override
  void onRequest(RequestOptions options, RequestInterceptorHandler handler) {
    super.onRequest(options, handler);
    //TODO add business logic
  }
}
