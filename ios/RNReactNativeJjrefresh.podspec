
Pod::Spec.new do |s|
  s.name         = "RNReactNativeJjrefresh"
  s.version      = "1.0.0"
  s.summary      = "RNReactNativeJjrefresh"
  s.description  = <<-DESC
                  RNReactNativeJjrefresh
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNReactNativeJjrefresh.git", :tag => "master" }
  s.source_files  = "RNReactNativeJjrefresh/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  