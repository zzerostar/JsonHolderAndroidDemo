import 'package:logger/logger.dart';

class LogUtil {
  late final Logger logger;

  LogUtil._internal() {
    logger =
        new Logger(printer: PrettyPrinter(methodCount: 0, printTime: true));
  }

  static late final LogUtil _instance = LogUtil._internal();

  factory LogUtil() => _instance;

  static Logger getLogger() {
    return _instance.logger;
  }

  static void d(
    dynamic message, {
    DateTime? time,
    Object? error,
    StackTrace? stackTrace,
  }) {
    _instance.logger
        .d(message, time: time, error: error, stackTrace: stackTrace);
  }

  static void e(
    dynamic message, {
    DateTime? time,
    Object? error,
    StackTrace? stackTrace,
  }) {
    _instance.logger
        .e(message, time: time, error: error, stackTrace: stackTrace);
  }
}
